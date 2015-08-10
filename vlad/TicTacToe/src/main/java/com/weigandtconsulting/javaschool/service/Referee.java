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

/**
 *
 * @author vlad
 */
public class Referee {

    private final GameFieldHelperImpl gameHelper = new GameFieldHelperImpl();
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;

    public Referee(TicTacToe playerTic, TicTacToe playerTac) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
    }

    public void startGame(Showable view, CellState startSign) {
        List<TicTacToe> generateTurns = generateTurns(startSign);
        List<CellState> gameField = gameHelper.getNewField();
        Game game;
        List<CellState> newStep;
        for (TicTacToe currentPlayer : generateTurns) {
            newStep = currentPlayer.nextStep(gameField);
            if (isCorrectTurn(gameField, newStep)) {
                gameField = newStep;
                view.refreshBattleField(gameField);
                game = gameHelper.analyzeGame(gameField);
                if (game.getState() == Game.State.OVER) {
                    System.out.println("Game is OVER ="+game);
                    break;
                }
            }
        }

    }

    boolean isCorrectTurn(List<CellState> gameFieldBefore, List<CellState> gameFieldAfter) {
        return true;
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

}
