package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 14/03/15.
 * TODO: http://www.codebytes.in/2014/08/minimax-algorithm-tic-tac-toe-ai-in.html
 */
public class AIPlayer extends Player {

    public AIPlayer(GameSigns currentPlayerMark) {
        super(currentPlayerMark);
    }

    @Override
    public GameField nextStep(GameField gameField) {

        System.out.println(gameField.getBoard().toString());

        if (gameField.getBoard().get(4) == GameSigns.EMPTY) {
            gameField.getBoard().set(4, currentPlayerMark);
            return gameField;

        } else {
            for (int i = 0; i < gameField.getBoard().size(); i = i+2)
                if (gameField.getBoard().get(i) == GameSigns.EMPTY) {
                    gameField.getBoard().set(i, currentPlayerMark);
                    return gameField;
                }
            for (int i = 1; i < gameField.getBoard().size()-1; i = i+2)
                if (gameField.getBoard().get(i) == GameSigns.EMPTY) {
                    gameField.getBoard().set(i, currentPlayerMark);
                    return gameField;
                }
        }

        return gameField;
    }

/*
public boolean canWinNext(GameField gameField){

//        /* Check Diagonal * /
//        if ((gameField.getBoard().get(0) == gameField.getBoard().get(4) &&
//                gameField.getBoard().get(4) == gameField.getBoard().get(8) &&
//                gameField.getBoard().get(0) == currentPlayerMark) ||
//                (gameField.getBoard().get(2) == gameField.getBoard().get(4) &&
//                        gameField.getBoard().get(4) == gameField.getBoard().get(6) &&
//                        gameField.getBoard().get(2) == currentPlayerMark)) {
//            return true;
//        }

/* Check rows * /
for (int i = 0; i < 7; i = i + 3) {
int[] num = {i,i+1,i+2};
int k = 0;
for(int j : num) {
if (gameField.getBoard().get(j).equals(currentPlayerMark)) k++;
}
if (k == 1) {}

if(gameField.getBoard().get(i) == gameField.getBoard().get(i + 1) &&
gameField.getBoard().get(i + 1) == gameField.getBoard().get(i + 2) &&
gameField.getBoard().get(i) == currentPlayerMark) {
return true;
}
}
}
*/


}
