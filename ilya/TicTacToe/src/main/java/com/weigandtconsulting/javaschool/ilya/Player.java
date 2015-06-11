package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 11/03/15.
 */
public class Player implements PlayTicTacToe {

    GameSigns currentPlayerMark;

    public GameSigns getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public Player(GameSigns currentPlayerMark) {
        this.currentPlayerMark = currentPlayerMark;
    }

//    public boolean hasNextStep(GameField gameField){
//        boolean isFull = false;
//        for (GameSigns aGameField : gameField.getBoard()) if (aGameField == GameSigns.EMPTY) isFull = true;
//        return isFull;
//    }

    public GameField nextStep(GameField gameField) {

        return gameField;

    }


}
