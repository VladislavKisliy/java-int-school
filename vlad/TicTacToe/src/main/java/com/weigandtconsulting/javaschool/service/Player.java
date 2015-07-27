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

import com.weigandtconsulting.javaschool.api.GameField;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vlad
 */
public class Player implements TicTacToe {

    private static final int WIN_SCORE = 10;
    private final CellState mySymbol;
    private final GameField innerGameField = new GameFieldImpl();
    
    public Player(CellState mySymbol) {
        this.mySymbol = mySymbol;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        List<CellState> result = null;
        return result;
    }

    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return !(innerGameField.isGameOver(gameField));
    }

    @Override
    public String getPlayerName() {
        return "Megamind (V.K) "+mySymbol;
    }

    int score(List<CellState> gameField, int depth) {
        int result = 0;
        if (innerGameField.isWinner(gameField, mySymbol)) {
            result = WIN_SCORE - depth;
        } else if (innerGameField.isWinner(gameField, CellState.getOpposite(mySymbol))) {
            result = depth - WIN_SCORE;
        }
        return result;
    }
    
    List<CellState> miniMax(List<CellState> gameField, int depth) {
        List<CellState> result = null;
        if (innerGameField.isGameOver(gameField)) {
            result = gameField;
        } else {
            List<Integer> scores = new ArrayList<>();
            List<Integer> moves = new ArrayList<>();
            depth++;
            List<Integer> availableMoves = innerGameField.getAvailableMoves(gameField);
            for (Integer availableMove : availableMoves) {
                
            }
        }
        return result;
    }
}
