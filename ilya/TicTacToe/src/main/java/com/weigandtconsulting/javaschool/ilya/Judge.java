package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 02/04/15.
 */
public interface Judge {

    boolean isWon(GameSigns currentPlayer, GameField gameField);
    boolean isCheatAfterMove(GameSigns currentPlayer, GameField gameField, int turnNumber);
    boolean hasNextStep(GameField gameField);
    boolean isGameOver(GameField gameField);


    }
