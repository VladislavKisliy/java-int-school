/*
 * Copyright (C) 2016 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.service;

import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.api.Referee;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.call.RequestCall;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class RefereeImpl implements Referee {

    private static final Logger LOG = Logger.getLogger(RefereeImpl.class.getName());

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final GameFieldHelper gameHelper = new GameFieldHelperImpl();
    private final Showable view;
    private final Map<CellState, TicTacToe> playersMap = new HashMap<>();
    private CellState activePlayer;
    private List<CellState> gameField;
    private CellState startSign;

    private List<CellState> generateTurns;

    public RefereeImpl(TicTacToe playerTic, TicTacToe playerTac, Showable view) {
        this.view = view;
        playersMap.put(CellState.TIC, playerTic);
        playersMap.put(CellState.TAC, playerTac);
    }

    @Override
    public void startGame(CellState startSign) {
        this.startSign = startSign;
        initNewGame();
    }

    @Override
    public void stopGame() {
        LOG.log(Level.INFO, "Stop threads");
//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        System.out.println("-- after end, after shut down. threads =" + threadSet.size());
//        for (Thread thread : threadSet) {
//            System.out.println("threads after =" + thread);
//        }
        for (Map.Entry<CellState, TicTacToe> entrySet : playersMap.entrySet()) {
            entrySet.getValue().stopGame();
        }
        executorService.shutdown();
        try {
            LOG.log(Level.INFO, "awaitTermination 2 sec");
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                LOG.log(Level.WARNING, "shutdown processes now");
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            LOG.log(Level.SEVERE, "awaitTermination problem", ex);
        }
//        threadSet = Thread.getAllStackTraces().keySet();
//        System.out.println("-- after end, after shut down. threads =" + threadSet.size());
//        for (Thread thread : threadSet) {
//            System.out.println("threads after =" + thread);
//        }
    }

    @Override
    public void update(Request request) {
        LOG.log(Level.INFO, "isFxApp ={0}", Platform.isFxApplicationThread());
        LOG.log(Level.INFO, "Thread name ={0}", Thread.currentThread().getName());
        RefereeRequest refereeRequest = request.getRefereeRequest();
        System.out.println("UPDATE: get from =" + request);
        if (refereeRequest == RefereeRequest.EMPTY) {
            if (request.getPlayerSign() == activePlayer) {
                LOG.log(Level.INFO, "get from ={0}", activePlayer);
                LOG.log(Level.INFO, "field ={0}", request.getGameField());

                List<CellState> newStep = request.getGameField();
                Game game;
                LOG.log(Level.INFO, "Pl: {0}. Req ={1}", new Object[]{activePlayer, request.getRefereeRequest()});
                LOG.log(Level.INFO, "Pl: {0}. Turn ={1}", new Object[]{activePlayer, gameField});
                if (gameHelper.isCorrectTurn(gameField, newStep)) {
                    gameField.clear();
                    gameField.addAll(newStep);
                    showBattleField(gameField);

                    game = gameHelper.analyzeGame(gameField);
                    if (game.getState() == Game.State.OVER) {
                        lockView();
                        System.out.println("Game is OVER =" + game);
                        System.out.println("Winner is " + activePlayer);
                    } else {
                        activePlayer = generateTurns.remove(0);
                        executorService.execute(new Thread(new RequestCall(playersMap.get(activePlayer), gameField, view)));
                    }

                }
            }
        } else {
            switch (refereeRequest) {
                case SURRENDER:
                    LOG.log(Level.INFO, "Dweeb. {0} lost the game", playersMap.get(activePlayer).getPlayerName());
                    break;
                case RESTART:
                    LOG.log(Level.INFO, "Ok, restart game. Asked {0}", playersMap.get(activePlayer).getPlayerName());
                    initNewGame();
                    break;
                case ERROR:
                    LOG.log(Level.INFO, "Error {0}", request.getMessage());
                    showError(request.getMessage());
                    break;
            }
        }
    }

    @Override
    public void startNewGame(CellState startSign, TicTacToe playerTic, TicTacToe playerTac) {
        playersMap.clear();
        playersMap.put(CellState.TIC, playerTic);
        playersMap.put(CellState.TAC, playerTac);
        startGame(startSign);
    }

    private void lockView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                view.lockBattleField();
            }
        });
    }

    private void showError(final String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                view.showErrorDialog(message);
            }
        });
    }

    private void showBattleField(final List<CellState> gameField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                view.refreshBattleField(gameField);
            }
        });
    }

    private void initNewGame() {
        generateTurns = generateTurns(startSign);
        gameField = gameHelper.getNewField();
        showBattleField(gameField);
        activePlayer = generateTurns.remove(0);
        executorService.execute(new Thread(new RequestCall(playersMap.get(activePlayer), gameField, view)));
    }

    private List<CellState> generateTurns(CellState startSign) {
        if (startSign == CellState.TOE) {
            throw new IllegalArgumentException("You should use correct signs");
        }
        List<CellState> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (startSign == CellState.TIC) {
                result.add(CellState.TIC);
                result.add(CellState.TAC);
            } else {
                result.add(CellState.TAC);
                result.add(CellState.TIC);
            }
        }
        return result;
    }
}
