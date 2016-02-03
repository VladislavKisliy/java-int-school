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
package com.weigandtconsulting.javaschool.call;

import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.controllers.FXMLController;
import com.weigandtconsulting.javaschool.players.HumanPlayer;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class RequestCall implements Runnable {

    private static final Logger LOG = Logger.getLogger(RequestCall.class.getName());
    private static final int DELAY = 3;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final TicTacToe activePlayer;
    private final List<CellState> gameField;
    private final Showable view;

    public RequestCall(TicTacToe activePlayer, List<CellState> gameField, Showable view) {
        this.activePlayer = activePlayer;
        this.gameField = gameField;
        this.view = view;
    }

    @Override
    public void run() {
        System.out.println("Start from =" + activePlayer);
        Future<?> futureRequest = executorService.submit(new Runnable() {
            @Override
            public void run() {
                activePlayer.getRequest(gameField);
            }
        });
        if (!(activePlayer instanceof HumanPlayer)) {
            computerPlayer(futureRequest);
        } else {
            try {
                humanPlayer(futureRequest);
            } catch (InterruptedException | ExecutionException ex) {
                LOG.log(Level.SEVERE, "Human player was interrupted", ex);
                stopWork();
            }
        }
        System.out.println("Referee died.");
    }

    private void humanPlayer(Future<?> futureRequest) throws InterruptedException, ExecutionException {
        futureRequest.get();
        stopWork();
    }

    private void computerPlayer(Future<?> futureRequest) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                view.lockBattleField();
            }
        });
        // try to get results earlier
        try {
            futureRequest.get(DELAY, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
//            LOG.log(Level.INFO, null, ex);
        }
        if (!futureRequest.isDone()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    view.showWaitingDailog(true);
                }
            });
            try {
                futureRequest.get();
            } catch (InterruptedException | ExecutionException ex) {
                LOG.log(Level.SEVERE, "Computer player was interrupted", ex);
                stopWork();
            } finally {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        view.showWaitingDailog(false);
                    }
                });
                stopWork();
            }
        }
    }

    private void stopWork() {
        executorService.shutdownNow();
        Thread.currentThread().interrupt();
    }
}
