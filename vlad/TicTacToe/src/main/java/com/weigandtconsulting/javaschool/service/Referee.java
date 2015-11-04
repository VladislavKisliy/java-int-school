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
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class Referee implements Observer {

    private final GameFieldHelperImpl gameHelper = new GameFieldHelperImpl();
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;
    private final Showable view;
    
    private List<TicTacToe> generateTurns;
    private Thread refereeThread;

    public Referee(TicTacToe playerTic, TicTacToe playerTac, Showable view) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
        this.view = view;
    }

    public void startGame(CellState startSign) {
        generateTurns = generateTurns(startSign);
        initNewGame();
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
        final List<CellState> gameField = gameHelper.getNewField();
        refereeThread = new Thread(new RefereeThread(gameField, generateTurns));
        refereeThread.start();
    }

    @Override
    public void update(RefereeRequest refereeRequest) {
        if (refereeThread != null) {
            if (refereeThread.isAlive()) {
                refereeThread.interrupt();
                System.out.println("Waiting for interrupt");
                try {
                    refereeThread.join(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        startGame(CellState.TAC);
    }

    private class RefereeThread implements Runnable {

        private final List<CellState> gameField;
        private final List<TicTacToe> generateTurns;

        public RefereeThread(List<CellState> gameField, List<TicTacToe> generateTurns) {
            this.gameField = gameField;
            this.generateTurns = generateTurns;
        }

        @Override
        public void run() {
            System.out.println("New referee was created");
            showBattleField(gameField);
            Game game;
            List<CellState> newStep;
            for (TicTacToe currentPlayer : generateTurns) {
                Request request = currentPlayer.getRequest(gameField);
                newStep = request.getGameField();
                System.out.println("Pl: " + currentPlayer.getPlayerName() + ". Req =" + request.getRefereeRequest());
                System.out.println("Pl: " + currentPlayer.getPlayerName() + ". Turn =" + gameField);
                RefereeRequest refereeRequest = request.getRefereeRequest();
                if (refereeRequest == RefereeRequest.EMPTY) {
                    if (isCorrectTurn(gameField, newStep)) {
                        gameField.clear();
                        gameField.addAll(newStep);
                        showBattleField(gameField);

                        game = gameHelper.analyzeGame(gameField);
                        if (game.getState() == Game.State.OVER) {
                            lockView();
                            System.out.println("Game is OVER =" + game);
                            System.out.println("Winner is " + currentPlayer.getPlayerName());
                            break;
                        }
                    }
                } else {
                    switch (refereeRequest) {
                        case SURRENDER:
                            System.out.println("Dweeb. " + currentPlayer.getPlayerName() + " lost the game");
                            break;
                        case RESTART:
                            System.out.println("Ok, restart game. Asked " + currentPlayer.getPlayerName());
                            initNewGame();
                            break;
                    }
                    // break loop
                    break;
                }
            }
            System.out.println("Referee died.");
        }
    }
}
