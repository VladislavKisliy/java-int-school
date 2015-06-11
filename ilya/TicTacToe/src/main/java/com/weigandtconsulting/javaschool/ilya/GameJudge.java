package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 02/04/15.
 * Game arbitr
 */
public class GameJudge implements Judge {

    public boolean isWon(GameSigns currentPlayer, GameField gameField) {

        /* Check Diagonal */
        if ((gameField.getBoard().get(0) == gameField.getBoard().get(4)
                && gameField.getBoard().get(4) == gameField.getBoard().get(8)
                && gameField.getBoard().get(0) == currentPlayer)
                || (gameField.getBoard().get(2) == gameField.getBoard().get(4)
                    && gameField.getBoard().get(4) == gameField.getBoard().get(6)
                    && gameField.getBoard().get(2) == currentPlayer)) {
            return true;
        }

        /* Check rows */
        for (int i = 0; i < 7; i = i + 3) {
            if(gameField.getBoard().get(i) == gameField.getBoard().get(i+1)
                    && gameField.getBoard().get(i+1) == gameField.getBoard().get(i+2)
                    && gameField.getBoard().get(i) == currentPlayer) {
                return true;
            }
        }

        /* Check Cols */
        for (int i = 0; i < 3; i++) {
            if(gameField.getBoard().get(i) == gameField.getBoard().get(i+3)
                    && gameField.getBoard().get(i+3) == gameField.getBoard().get(i+6)
                    && gameField.getBoard().get(i) == currentPlayer) {
                return true;
            }
        }

        return false;

    }

    public boolean isCheatAfterMove(GameSigns currentPlayer, GameField gameField, int turnNumber) {
        int i = 0;

        for (GameSigns gameSign : gameField.getBoard()) {
            i = gameSign == currentPlayer ? i+1 : i;
        }

        return i != turnNumber;
    }

    public boolean hasNextStep(GameField gameField){
        return gameField.getBoard().contains(GameSigns.EMPTY);
    }

    public boolean isGameOver(GameField gameField) {
        return (isWon(GameSigns.CROSS, gameField) || isWon(GameSigns.ZERO, gameField) || !hasNextStep(gameField));
    }


}
