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
package com.weigandtconsulting.javaschool.players;

import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vlad
 */
public class Player extends BaseTicTacToe {

    private static final int WIN_SCORE = 10;
    private final GameFieldHelper innerGameField = new GameFieldHelperImpl();
    private final int[] bestStartSolutions = {0, 2, 6, 8, 4};
    private final Random random = new Random();

    public Player() {
        super();
    }

    public Player(CellState playerSign) {
        super(playerSign);
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        if (gameField == null) {
            throw new IllegalArgumentException("Incompatible input parameter");
        }
        Integer bestStep;
        if (innerGameField.isFieldEmpty(gameField)) {
            bestStep = randomStep();
        } else {
            bestStep = miniMax(gameField, 0);
        }
        if (bestStep < gameField.size()) {
            lastTurn = innerGameField.doStep(gameField, playerSign, bestStep);
            notifyObservers();
        }

        return new ArrayList<>(lastTurn);
    }

    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return !(innerGameField.isGameOver(gameField));
    }

    @Override
    public String getPlayerName() {
        return "Megamind (V.K) " + playerSign;
    }

    @Override
    public String toString() {
        return "Computer Player{" + "playerSymbol=" + playerSign + '}';
    }

    /**
     * default visibility for testing purpose
     *
     * @param gameField
     * @param depth
     * @param playerSign
     * @return
     */
    int score(List<CellState> gameField, int depth, CellState playerSign) {
        int result = 0;
        if (innerGameField.isWinner(gameField, playerSign)) {
            result = WIN_SCORE - depth;
        } else if (innerGameField.isWinner(gameField, CellState.getOpposite(playerSign))) {
            result = depth - WIN_SCORE;
        }
        return result;
    }

    Integer miniMax(List<CellState> gameField, int depth) {
        Integer result;
        if (innerGameField.isGameOver(gameField)) {
            result = score(gameField, depth, playerSign);
        } else {
            List<Integer> scores = new ArrayList<>();
            List<Integer> moves = new ArrayList<>();
            depth++;
            CellState activeSign = getSign(playerSign, depth);

            List<Integer> availableMoves = innerGameField.getAvailableMoves(gameField);
            for (Integer newMove : availableMoves) {
                List<CellState> newGameField = innerGameField.doStep(gameField, activeSign, newMove);
                scores.add(miniMax(newGameField, depth));
                moves.add(newMove);
            }
            int scoreIndex;
            if (activeSign == playerSign) {
                scoreIndex = scores.indexOf(Collections.max(scores));
            } else {
                scoreIndex = scores.indexOf(Collections.min(scores));
            }
            if (depth == 1) {
                result = moves.get(scoreIndex);
            } else {
                result = scores.get(scoreIndex);
            }
        }
        return result;
    }

    /**
     * default visibility for testing purpose
     *
     * @param playerSign
     * @param depth
     * @return
     */
    CellState getSign(CellState playerSign, int depth) {
        CellState result = playerSign;
        if (depth % 2 == 0) {
            result = CellState.getOpposite(playerSign);
        }
        return result;
    }

    private Integer randomStep() {
        int randomNum = random.nextInt(bestStartSolutions.length);
        return bestStartSolutions[randomNum];
    }
}
