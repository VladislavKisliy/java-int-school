/*
 * Copyright (C) 2015 Weigandt Consulting
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

import com.weigandtconsulting.javaschool.api.Observer;
import com.weigandtconsulting.javaschool.api.Referee;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.call.RequestCall;
import com.weigandtconsulting.javaschool.controllers.FXMLController.HumanPlayer;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class RefereeImpl implements Referee {

    private final GameFieldHelperImpl gameHelper = new GameFieldHelperImpl();
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;
    private final Showable view;
    private TicTacToe activePlayer;
    private List<CellState> gameField;
    private CellState startSign;

    private List<TicTacToe> generateTurns;
    private Thread refereeThread;

    public RefereeImpl(TicTacToe playerTic, TicTacToe playerTac, Showable view) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
        this.view = view;
    }

    @Override
    public void startGame(CellState startSign) {
        this.startSign = startSign;
        initNewGame();
    }

    @Override
    public void stopGame() {
        if (refereeThread != null) {
            refereeThread.interrupt();
        }
    }

    boolean isCorrectTurn(List<CellState> gameFieldBefore, List<CellState> gameFieldAfter) {
        int changeCounter = 0;
        boolean result = false;
        List<Integer> availableMoves = gameHelper.getAvailableMoves(gameFieldBefore);
        for (Integer index : availableMoves) {
            if (gameFieldAfter.get(index) != CellState.TOE) {
                changeCounter++;
            }
        }
        if (changeCounter == 1) {
            result = true;
        }
        return result;
    }

    private List<TicTacToe> generateTurns(CellState startSign) {
        if (startSign == CellState.TOE) {
            throw new IllegalArgumentException("You should use correct signs");
        }
        List<TicTacToe> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (startSign == CellState.TIC) {
                result.add(playerTic);
                result.add(playerTac);
            } else {
                result.add(playerTac);
                result.add(playerTic);
            }
        }
        return result;
    }

    private void lockView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                view.lockBattleField();
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
        refereeThread = new Thread(new RequestCall(activePlayer, gameField, view));
        refereeThread.start();
    }

    @Override
    public void update(Request request) {
        System.out.println("isFxApp =" + Platform.isFxApplicationThread());
        System.out.println("Thread name =" + Thread.currentThread().getName());
        RefereeRequest refereeRequest = request.getRefereeRequest();
        System.out.println("UPDATE: get from =" + request);
        if (refereeRequest == RefereeRequest.EMPTY) {
            if (request.getPlayer() == activePlayer) {
                System.out.println("get from =" + activePlayer.getPlayerName());
                System.out.println("field =" + request.getGameField());

                List<CellState> newStep = request.getGameField();
                Game game;
                System.out.println("Pl: " + activePlayer.getPlayerName() + ". Req =" + request.getRefereeRequest());
                System.out.println("Pl: " + activePlayer.getPlayerName() + ". Turn =" + gameField);
                if (isCorrectTurn(gameField, newStep)) {
                    gameField.clear();
                    gameField.addAll(newStep);
                    showBattleField(gameField);

                    game = gameHelper.analyzeGame(gameField);
                    if (game.getState() == Game.State.OVER) {
                        lockView();
                        System.out.println("Game is OVER =" + game);
                        System.out.println("Winner is " + activePlayer.getPlayerName());
                    } else {
                        System.out.println("## refereeThread interrupted=" + refereeThread.isInterrupted());
                        System.out.println("## refereeThread alive=" + refereeThread.isAlive());
                        System.out.println("## refereeThread interrupted=" + refereeThread.isDaemon());
//                        if ((refereeThread != null)&& (refereeThread.isInterrupted())) {
//                            
//                        }
                        activePlayer = generateTurns.remove(0);
                        refereeThread = new Thread(new RequestCall(activePlayer, gameField, view));
                        refereeThread.start();
                    }

                }
            }
        } else {
            switch (refereeRequest) {
                case SURRENDER:
                    System.out.println("Dweeb. " + activePlayer.getPlayerName() + " lost the game");
                    break;
                case RESTART:
                    System.out.println("Ok, restart game. Asked " + activePlayer.getPlayerName());
                    initNewGame();
                    break;
            }
        }
    }
}
