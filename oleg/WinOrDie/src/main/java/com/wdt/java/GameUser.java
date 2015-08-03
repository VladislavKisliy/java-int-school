/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winordie;

import java.util.List;

/**
 *
 * @author Oleg
 */
public class GameUser implements IGameUser{
    private final String name;
    private final CellState signToGo;
    public static void main(String[] args) {
    }
    public GameUser(String inName,CellState inSignToGo){
        this.name=inName;
        this.signToGo=inSignToGo;
    }
    public String getName(){return this.name;}
    public CellState getSignToGo(){return this.signToGo;}
    public List<CellState> nextStep(List<CellState> battleField){
        return battleField;
    }
}
