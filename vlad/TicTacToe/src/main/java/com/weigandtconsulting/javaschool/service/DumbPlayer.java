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

import com.weigandtconsulting.javaschool.api.BaseTicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Only for async testing
 *
 * @author vlad
 */
public class DumbPlayer extends BaseTicTacToe {

    private final int DELAY = 5;
    private final CellState playerSymbol;

    public DumbPlayer(CellState playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        List<CellState> result = gameField;
        try {
            TimeUnit.SECONDS.sleep(DELAY);
            for (int i = 0; i < gameField.size(); i++) {
                CellState cell = gameField.get(i);
                if (cell == CellState.TOE) {
                    gameField.set(i, playerSymbol);
                    break;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DumbPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return true;
    }

    @Override
    public String getPlayerName() {
        return "Really slow player";
    }
}
