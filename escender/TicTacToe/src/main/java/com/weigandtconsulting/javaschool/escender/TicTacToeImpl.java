/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weigandtconsulting.javaschool.escender;

import java.util.List;
import java.util.Random;

/**
 *
 * @author W
 */
public class TicTacToeImpl implements TicTacToe {

    private GameState player;
    private Integer widthField;
    private Integer heightFiled;
    private Integer winLength;

    public TicTacToeImpl(GameState player, Integer rowCount) {
        this.player = player;
        this.widthField = rowCount;
        this.heightFiled = rowCount;
        this.winLength = rowCount;
    }

    public TicTacToeImpl(GameState player, Integer widthField, Integer heightFiled, Integer winLength) {
        this.player = player;
        this.widthField = widthField;
        this.heightFiled = heightFiled;
        this.winLength = winLength;
    }

    @Override
    public List<GameState> nextStep(List<GameState> gameField) {
        Random rn = new Random();
        int i;
        while (true) {
            i = rn.nextInt(widthField * heightFiled);
            if (gameField.get(i) == GameState.EMPTY) {
                gameField.set(i, player);
                break;
            }
        }
        return gameField;
    }

    @Override
    public boolean hasNextStep(List<GameState> gameField) {
        boolean res = false;
        for (int i = 0; i < gameField.size(); i++) {
            if (gameField.get(i) == GameState.EMPTY) {
                res = true;
            }
        }
        return res;
    }
}
