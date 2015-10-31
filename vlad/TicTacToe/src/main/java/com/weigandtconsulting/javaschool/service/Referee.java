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

import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class Referee {

    private final GameFieldHelperImpl gameHelper = new GameFieldHelperImpl();
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;
    private final Showable view;

    public Referee(TicTacToe playerTic, TicTacToe playerTac, Showable view) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
        this.view = view;
    }

    public void startGame(CellState startSign) {
        final List<TicTacToe> generateTurns = generateTurns(startSign);
        final List<CellState> gameField = gameHelper.getNewField();
        Runnable runThread = new Runnable() {
            @Override
            public void run() {
                Game game;
                List<CellState> newStep;
                for (TicTacToe currentPlayer : generateTurns) {
                    newStep = currentPlayer.nextStep(gameField);
                    System.out.println("Pl: " + currentPlayer.getPlayerName() + ". Turn =" + gameField);
                    if (isCorrectTurn(gameField, newStep)) {
                        gameField.clear();
                        gameField.addAll(newStep);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                view.refreshBattleField(gameField);
                            }
                        });

                        game = gameHelper.analyzeGame(gameField);
                        if (game.getState() == Game.State.OVER) {
                            lockView();
                            System.out.println("Game is OVER =" + game);
                            System.out.println("Winner is " + currentPlayer.getPlayerName());
                            break;
                        }
                    }
                }
            }
        };
        new Thread(runThread).start();
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
}
