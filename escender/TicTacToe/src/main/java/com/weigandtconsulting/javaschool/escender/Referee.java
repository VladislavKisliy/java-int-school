/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weigandtconsulting.javaschool.escender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author W
 */
public class Referee {

    private Integer widthField;
    private Integer heightFiled;
    private Integer winLength;
    private ArrayList<GameState> gameFieldPrev;
    private List<GameState> gameFieldCur;
    private List<TicTacToe> players;
    private Showable show;

    Referee(Integer rowLength, List<GameState> gameField) {
        widthField = rowLength;
        heightFiled = rowLength;
        winLength = rowLength;
        gameFieldPrev = new ArrayList<GameState>(gameField);
        gameFieldCur = new ArrayList<GameState>(gameField);
        players = new ArrayList<TicTacToe>();
        show = new ShowableImpl(rowLength);
    }

    Referee(Integer widthField, Integer heightFiled, Integer winLength, List<GameState> gameField) {
        this.widthField = widthField;
        this.heightFiled = heightFiled;
        this.winLength = winLength;
        gameFieldPrev = new ArrayList<GameState>(gameField);
        gameFieldCur = new ArrayList<GameState>(gameField);
        players = new ArrayList<TicTacToe>();
        show = new ShowableImpl(widthField, heightFiled);
    }

    public void addPlayer(TicTacToe player) {
        players.add(player);
    }

    public boolean checkPlayStep(List<GameState> gameFieldNext) {
        boolean result = true;
        int countChangeState = 0;
        for (int i = 0; i < gameFieldNext.size(); i++) {
            if (gameFieldNext.get(i) != gameFieldPrev.get(i)) {
                countChangeState++;
                if (countChangeState > 1) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public Integer horizonWin(Integer iterator, Integer countChangeState) {
        if (iterator % widthField < (iterator + 1) % widthField
                && gameFieldCur.get(iterator) == gameFieldCur.get(iterator + 1)) {
            countChangeState = horizonWin(iterator + 1, countChangeState + 1);
        }
        return countChangeState;
    }

    public Integer verticalWin(Integer iterator, Integer countChangeState) {
        if (iterator + widthField < widthField * heightFiled
                && gameFieldCur.get(iterator) == gameFieldCur.get(iterator + widthField)) {
            countChangeState = verticalWin(iterator + widthField, countChangeState + 1);
        }
        return countChangeState;
    }

    public Integer rightDiagonal(Integer iterator, Integer countChangeState) {
        if ((iterator + widthField + 1) % widthField > iterator % widthField
                && iterator + 1 + widthField < widthField * heightFiled
                && gameFieldCur.get(iterator) == gameFieldCur.get(iterator + widthField + 1)) {
            countChangeState = rightDiagonal(iterator + widthField + 1, countChangeState + 1);
        }
        return countChangeState;
    }

    public Integer leftDiagonal(Integer iterator, Integer countChangeState) {
        if ((iterator + widthField - 1) % widthField < iterator % widthField
                && iterator - 1 + widthField < heightFiled * widthField
                && gameFieldCur.get(iterator) == gameFieldCur.get(iterator + widthField - 1)) {
            countChangeState = leftDiagonal(iterator + widthField - 1, countChangeState + 1);
        }
        return countChangeState;
    }

    private boolean checkWin(List<GameState> gameField) {
        boolean result = false;
        for (int i = 0; i < gameField.size(); i++) {
            if (gameField.get(i) != GameState.EMPTY) {
                if (winLength.equals(horizonWin(i, 1))) {
                    System.out.println("horizonWin:" + i);
                    result = true;
                    break;
                }
                if (winLength.equals(verticalWin(i, 1))) {
                    System.out.println("verticalWin:" + i);
                    result = true;
                    break;
                }
                if (winLength.equals(leftDiagonal(i, 1))) {
                    System.out.println("leftDiagonal:" + i);
                    result = true;
                    break;
                }
                if (winLength.equals(rightDiagonal(i, 1))) {
                    System.out.println("rightDiagonal:" + i);
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public void startGame() {
        int i = 0;
        boolean winInd = false;
        while (true) {
            Collections.copy(gameFieldPrev, gameFieldCur);
            if (!players.get(0).hasNextStep(gameFieldCur)) {
                break;
            }
            i = (i + 1) % players.size();
            gameFieldCur = players.get(i).nextStep(gameFieldCur);
            if (!checkPlayStep(gameFieldCur)) {
                System.out.println("Cheater");
                break;
            }

            show.refreshBattleField(gameFieldCur);
            if (checkWin(gameFieldCur)) {
                winInd = true;
                break;
            }
        }
        if (winInd) {
            System.out.println("WIN Plaers:" + i);
        } else {
            System.out.println("Dead heat");
        }

    }

    public Integer applyStep(List<GameState> gameFieldCur) {
        int result = 0;
        this.gameFieldCur = gameFieldCur;
        if (!checkPlayStep(gameFieldCur)) {
            System.out.println("Cheater");
            result = 2;
            Collections.copy(gameFieldCur, gameFieldPrev);
            return result;
        }
        if (!players.get(0).hasNextStep(gameFieldCur)) {
            result = 3;
            return result;
        }

        if (checkWin(gameFieldCur)) {
            result = 1;
            return result;
        }
        Collections.copy(gameFieldPrev, gameFieldCur);
        return result;

    }
}
