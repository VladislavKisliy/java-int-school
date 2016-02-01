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

import com.weigandtconsulting.javaschool.api.Observer;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.Game;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
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
public class RefereeAsyncWrapper implements Observer {

    private static final Logger LOG = Logger.getLogger(RefereeAsyncWrapper.class.getName());

//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService;
    private final GameFieldHelperImpl gameHelper = new GameFieldHelperImpl();
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;
    private final Showable view;
    private  TicTacToe activePlayer;

    private List<TicTacToe> generateTurns;

    public RefereeAsyncWrapper(TicTacToe playerTic, TicTacToe playerTac, Showable view) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
        this.view = view;
    }

    public void startGame(final CellState startSign) {
        if (executorService != null) {
            stopGame();
        }
        executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                generateTurns = generateTurns(startSign);
                initNewGame();
            }
        });
    }

    public void stopGame() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- after end, after shut down. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads after =" + thread);
        }
        try {
            executorService.shutdown();
            LOG.log(Level.INFO, "awaitTermination 2 sec");
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                LOG.log(Level.WARNING, "shutdown processes now");
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RefereeAsyncWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- after end, after shut down. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads after =" + thread);
        }
//        Thread.currentThread().interrupt();
    }

    /**
     * Check correctness input data
     *
     * @param gameFieldBefore
     * @param gameFieldAfter
     * @return
     */
    private boolean isCorrectTurn(List<CellState> gameFieldBefore, List<CellState> gameFieldAfter) {
        int changeCounter = 0;
        boolean result = false;
        System.out.println("Check before =" + gameFieldBefore);
        System.out.println("Check after =" + gameFieldAfter);
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
        System.out.println("New referee was created");
        final List<CellState> gameField = gameHelper.getNewField();
        showBattleField(gameField);
        List<CellState> newStep;
        Request request;
        Game game;
        for (final TicTacToe currentPlayer : generateTurns) {
            request = getAsyncRequest(currentPlayer, gameField);
            if (executorService.isShutdown()) {
                System.out.println("Go away!");
                break;
            }
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
                } else {
                    LOG.log(Level.SEVERE, "Incorrect turn!");
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

    private Request getAsyncRequest(final TicTacToe currentPlayer, final List<CellState> gameField) {
        Request result = null;
        System.out.println("~~ future");
        Future<Request> nextFutureStep = executorService.submit(wrapStepToCallable(currentPlayer, gameField));
        Request request = null;
        // Quick try to get result
        try {
            request = nextFutureStep.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            // Just ignore this exception
//                Logger.getLogger(RefereeAsyncWrapper.class.getName()).log(Level.SEVERE, null, ex);

        }
        if (request == null) {
            try {
                while (!nextFutureStep.isDone()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
//                                view.showWaitingDailog(true);
                        }
                    });
                    System.out.println("wait. nextFutureStep. isCancelled =" + nextFutureStep.isCancelled() + ", isDone=" + nextFutureStep.isDone());
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RefereeAsyncWrapper.class.getName()).log(Level.SEVERE, null, ex);
                // Re-assert the thread's interrupted status
                Thread.currentThread().interrupt();
                // We don't need the result, so cancel the task too
                nextFutureStep.cancel(true);
            } finally {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
//                            view.showWaitingDailog(false);
                    }
                });
            }
        }
        if (request == null) {
            try {
                request = nextFutureStep.get(100, TimeUnit.MILLISECONDS);
                System.out.println("## --" + request);
                result = request;
            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                Logger.getLogger(RefereeAsyncWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            result = request;
        }
        return result;
    }

    private Callable wrapStepToCallable(final TicTacToe player, final List<CellState> gameField) {
        return new Callable<Request>() {
            @Override
            public Request call() throws Exception {
                Request result = new Request(player);
                result.setRefereeRequest(RefereeRequest.EMPTY);
                result.setGameField(player.nextStep(gameField));
                return result;
            }
        };
    }

//    @Override
//    public void update(RefereeRequest refereeRequest) {
//        switch (refereeRequest) {
//            case SURRENDER:
//                System.out.println("Dweeb. lost the game");
//                break;
//            case RESTART:
//                System.out.println("Ok, restart game. Asked ");
//                startGame(CellState.TIC);
//                break;
//        }
//        // break loop
////        stopGame();
//    }

    @Override
    public void update(Request request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
