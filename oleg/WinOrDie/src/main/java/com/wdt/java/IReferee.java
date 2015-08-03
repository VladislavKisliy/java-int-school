/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winordie;

import java.util.List;

/**
 *
 * @author Rustem
 */
public interface IReferee {
//    void defineUsers(GameUser user1, GameUser user2);
    boolean cheated(List<CellState> battleField);
    boolean won(List<CellState> battleField);
    boolean hasNextStep(List<CellState> gameField);
}
