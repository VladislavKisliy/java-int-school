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
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Only for async testing
 *
 * @author vlad
 */
public class DumbPlayer extends BaseTicTacToe {

    private static final Logger LOG = Logger.getLogger(DumbPlayer.class.getName());

//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
//    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final int DELAY = 4;
    private final CellState playerSymbol;

    public DumbPlayer(CellState playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        List<CellState> result = new ArrayList<>(gameField);
        for (int i = 0; i < result.size(); i++) {
            CellState cell = result.get(i);
            if (cell == CellState.TOE) {
                result.set(i, playerSymbol);
                break;
            }
        }
        try {
            TimeUnit.SECONDS.sleep(DELAY);
        } catch (InterruptedException ex) {
            LOG.log(Level.SEVERE, "Interrupted step", ex);
        }
        lastTurn = result;
        notifyObservers();
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
