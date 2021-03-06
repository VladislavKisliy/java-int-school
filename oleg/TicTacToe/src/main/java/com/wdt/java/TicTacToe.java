/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Oleg
 */
public  class TicTacToe implements ITicTacToe{
    String playerName;
    CellState signToGo;
    /**
     * @param args the command line arguments
     */
    public TicTacToe(String inPName, CellState inSignToGo){
        List<CellState> gameField = new ArrayList<CellState>((int)(Math.pow(Constants.DIMENSION,2)));
        this.playerName=inPName;
        this.signToGo=inSignToGo;
    }
    public static void main(String[] args) {
        List<CellState> battleField =new ArrayList<CellState>();
        //temp part to fill battlefield with random values
        GUIMainForm MyForm=new GUIMainForm();
        MyForm.setVisible(true);        
//        int dim=Constants.DIMENSION;
//        Random rand=new Random();
//        for (int i=0;i<dim*dim;i++){
//            int tempRand=rand.nextInt(3);
//            if(tempRand==0){
//                battleField.add(CellState.ZERO);
//            }else if(tempRand==1){
//                battleField.add(CellState.CROSS);
//            }else{
//                battleField.add(CellState.NONE);
//            }
//        System.out.println(battleField.get(i).getEntity());
       // }
        DrawBattle testDraw=new DrawBattle();
       // testDraw.refreshBattleField(battleField);
        
    }
    public List<CellState> nextStep(List<CellState> gameField){
        int step = gameField.indexOf(CellState.NONE);
        gameField.add(step, this.signToGo);
        return gameField;
    }
    public boolean hasNextStep(List<CellState> gameField){
        return gameField.contains(CellState.NONE);
    }
    public String getPlayerName(){
        return this.playerName;
    }    
}