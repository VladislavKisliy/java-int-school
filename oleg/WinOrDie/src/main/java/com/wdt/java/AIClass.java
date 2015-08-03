/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleg
 */
public class AIClass implements AI {
    public List<CellState> nextStepAI(List<CellState> gameField){
        List<CellState> tmp=new ArrayList<CellState>();
        tmp.add(CellState.ZERO);
        return tmp;
    }
    //method to get all possible sets of positions
    //which should be filled to win the game
    //depending on dimenton of GameField
    public static List<Integer> getVariantsToCheck(int dimention){
        List<Integer> listOfPositions=new ArrayList<Integer>();
        //horizontal set of values
        //like [{1,2,3},{4,5,6},{7,8,9}] for dimention=3
        for(int i=0;i<pow(dimention,2);i+=dimention){
            for(int j=1;j<=dimention;j++){
                listOfPositions.add(i+j);
            }
        }
        //vertical set of values
        //like [{1,4,7},{2,5,8},{3,6,9}] for dimention=3
        for(int i=1;i<=dimention;i++){
            for(int j=0;j<dimention;j++){
                listOfPositions.add(i+j*dimention);
            }
        }
        //crossing sequence of values
        //like {1,5,9} for dimention=3
        for(int i=0;i<dimention;i++){
            listOfPositions.add(1+(dimention+1)*i);        
        }
        //opposite crossing sequence of values
        //like {3,5,7} for dimention=3
        for(int i=0;i<dimention;i++){
            listOfPositions.add(dimention+(dimention-1)*i);        
        }
        return listOfPositions;
    }
}
