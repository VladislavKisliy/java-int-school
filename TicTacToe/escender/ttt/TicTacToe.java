/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt;

import java.util.List;

/**
 *
 * @author W
 */
public interface TicTacToe {
    List<GameState> nextStep(List<GameState> gameField);
    boolean hasNextStep(List<GameState> gameField);
}
