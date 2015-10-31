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

import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vlad
 */
public class GameFieldHelperImpl implements GameFieldHelper {

    private static final int LINE_SIZE = 3;
    public static final int CELL_AMOUNT = LINE_SIZE * LINE_SIZE;
    private static final int[][] WINNER_COMBINATIONS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        {0, 4, 8}, {2, 4, 6},
        {1, 4, 7}, {3, 4, 5},
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}
    };
    private static final List<CellState> EMPTY_GAME_FIELD = new ArrayList<>(CELL_AMOUNT);

    static {
        for (int i = 0; i < CELL_AMOUNT; i++) {
            EMPTY_GAME_FIELD.add(CellState.TOE);
        }
    }

    @Override
    public Boolean isWinner(List<CellState> gameField, CellState player) {
        Boolean result = Boolean.FALSE;
        if (gameField.size() > CELL_AMOUNT) {
            throw new IllegalArgumentException("Wrong game field!");
        }
        for (int[] winnerCombination : WINNER_COMBINATIONS) {
            int successCounter = 0;
            for (int j = 0; j < winnerCombination.length; j++) {
                int x = winnerCombination[j];
                if (gameField.get(x) == player) {
                    successCounter++;
                } else {
                    break;
                }
                if (successCounter == LINE_SIZE) {
                    result = Boolean.TRUE;
                }
            }
            if (result == Boolean.TRUE) {
                break;
            }
        }
        return result;
    }

    @Override
    public Boolean isGameOver(List<CellState> gameField) {
        Boolean result = Boolean.FALSE;
        if (analyzeGame(gameField).getState() == Game.State.OVER) {
            result = Boolean.TRUE;
        }
        return result;
    }

    @Override
    public Boolean isFieldEmpty(List<CellState> gameField) {
        boolean result = false;
        List<Integer> availableMoves = getAvailableMoves(gameField);
        if (availableMoves.size() == CELL_AMOUNT) {
            result = true;
        }
        return result;
    }

    @Override
    public List<CellState> getNewField() {
        return new ArrayList<>(EMPTY_GAME_FIELD);
    }

    @Override
    public List<Integer> getAvailableMoves(List<CellState> gameField) {
        List<Integer> result = new ArrayList<>(CELL_AMOUNT);
        int positionCounter = 0;
        for (CellState cell : gameField) {
            if (cell == CellState.TOE) {
                result.add(positionCounter);
            }
            positionCounter++;
        }
        return result;
    }

    @Override
    public List<CellState> doStep(List<CellState> gameField, CellState playerSign, Integer position) {
        CellState[] result = gameField.toArray(new CellState[CELL_AMOUNT]);
        if (result[position] == CellState.TOE) {
            result[position] = playerSign;
        } else {
            throw new IllegalArgumentException("We can insert only in empty cells");
        }

        return Arrays.asList(result);
    }

    @Override
    public Game analyzeGame(List<CellState> gameField) {
        Game game = new Game();
        if (isWinner(gameField, CellState.TIC)) {
            game.setState(Game.State.OVER);
            game.setResult(Game.Result.WIN);
            game.setWinnerSign(CellState.TIC);
        } else if (isWinner(gameField, CellState.TAC)) {
            game.setState(Game.State.OVER);
            game.setResult(Game.Result.WIN);
            game.setWinnerSign(CellState.TAC);
        } else if (isFieldFull(gameField)) {
            game.setState(Game.State.OVER);
            game.setResult(Game.Result.DRAW);
        } else if (gameField.equals(getNewField())) {
            game.setState(Game.State.START);
        } else {
            game.setState(Game.State.CONTINUE);
        }
        return game;
    }

    private boolean isFieldFull(List<CellState> gameField) {
        boolean result = true;
        for (CellState cell : gameField) {
            if (cell == CellState.TOE) {
                result = false;
                break;
            }
        }
        return result;
    }
}
