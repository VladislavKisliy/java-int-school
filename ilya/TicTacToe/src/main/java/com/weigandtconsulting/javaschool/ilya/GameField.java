package com.weigandtconsulting.javaschool.ilya;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibeketov on 25/03/15.
 */
public class GameField {

    public static final Integer LINES_NUM = 3;

    private List<GameSigns> board;

    public GameField(int linesNum) {
        List<GameSigns> gameField = new ArrayList<GameSigns>(linesNum);

        for (int i=0; i < (linesNum*linesNum); i++) {
            gameField.add(i, GameSigns.EMPTY);
        }

        this.board = gameField;
    }

    public List<GameSigns> getBoard() {
        return board;
    }

    public void setBoard(List<GameSigns> board) {
        this.board = board;
    }

    public static Integer getLINES_NUM() {
        return LINES_NUM;
    }

    public boolean hasNextStep(){
//        for (GameSigns aGameField : board) if (aGameField == GameSigns.EMPTY) return true;
        return this.board.contains(GameSigns.EMPTY);
    }

}
