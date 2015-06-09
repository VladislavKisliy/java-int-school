/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weigandtconsulting.javaschool.escender;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author W
 */
public class TTTGame {

    private GameState whoPlayed;
    private List<GameState> gameField;
    private Integer rowLength;
    private Integer winLength;
    private Integer widthField;
    private Integer heightField;
    private Referee referee;
    private boolean gameOver;
    private List<GameState> compPlayers;

    public boolean isGameOver() {
        return gameOver;
    }

    public TTTGame(Integer rowLength) {
        this.rowLength = rowLength;
        this.winLength = rowLength;
        this.heightField = rowLength;
        this.widthField = rowLength;
        gameOver = false;
        whoPlayed = GameState.TIC;
        gameField = new ArrayList<GameState>();
        for (int i = 0; i < widthField * heightField; i++) {
            gameField.add(GameState.EMPTY);
        };

        referee = new Referee(widthField, heightField, winLength, gameField);
        referee.addPlayer(new TicTacToeImpl(GameState.TIC, widthField, heightField, winLength));
        referee.addPlayer(new TicTacToeImpl(GameState.TAC, widthField, heightField, winLength));
        //referee.startGame();
    }

    protected String gameStep(Integer fieldCell) {
        String msgText = null;
        if (gameOver) {
            whoPlayed = GameState.EMPTY;
            msgText = "Game Over";
        } else {
            gameField.set(fieldCell, whoPlayed);
            int gameStatus = referee.applyStep(gameField);
            switch (gameStatus) {
                case 0:
                    if (whoPlayed == GameState.TIC) {
                        whoPlayed = GameState.TAC;
                    } else {
                        whoPlayed = GameState.TIC;
                    }
                    break;
                case 3:
                    msgText = "Dead heat";
                    break;
                case 2:
                    msgText = "Cheater";                   
                    break;
                case 1:
                    msgText = "Game Over Win:"+whoPlayed.toString();
                    whoPlayed = GameState.EMPTY;                    
                    gameOver = true;
                    break;

            }
            

        }
        return msgText;
    }

    public GameState getWhoPlayed() {
        return whoPlayed;
    }

    public List<GameState> getGameField() {
        return gameField;
    }
}
