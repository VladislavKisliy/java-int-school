package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 11/03/15.
 */
public interface PlayTicTacToe {

    GameField nextStep(GameField gameField);
//    boolean hasNextStep(GameField gameField);
    GameSigns getCurrentPlayerMark();

    }
