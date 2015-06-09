/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weigandtconsulting.javaschool.escender;

/**
 *
 * @author W
 */
public enum GameState {
    TIC("X"), 
    TAC("O"),
    EMPTY(" ");
    
    private String name;

    private GameState(String name) {
        this.name = name;
    }
  
    @Override
    public String toString() {
        return name;
    }
    
}
