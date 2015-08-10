/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.List;

/**
 *
 * @author Oleg
 */
public class DrawBattle implements Showable{
    public void refreshBattleField(List<CellState> gameField) {
        char charToDraw = Constants.CHAR_TO_DRAW;
        int dimention= Constants.DIMENSION;
        StringBuilder lineToDraw=new StringBuilder();
        
        System.out.println(drawTopBottomEdge(charToDraw,dimention));
        for(int i=0;i<dimention;i++){
            for(int j=0;j<dimention;++j){
                lineToDraw.append(charToDraw).append(gameField.get(i+j).getEntity());
                if(j==dimention-1){lineToDraw.append(charToDraw);}
            }
            System.out.println(lineToDraw);
            lineToDraw.delete(0, lineToDraw.length());
            System.out.println(drawTopBottomEdge(charToDraw,dimention));
        }
    }
    private StringBuilder drawTopBottomEdge(char charToDraw,int dim){
        StringBuilder resultLine=new StringBuilder();
        for(int i=0; i<(dim*3-(dim-1));i++){
           resultLine.append(charToDraw);
        }
        return resultLine;
    }
    
}
