package com.wdt.java;


import com.wdt.java.Constants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oleg
 */
public enum CellState{
    ZERO(Constants.SIGN_ZERO),
    CROSS(Constants.SIGN_CROSS),
    NONE(Constants.SIGN_NULL);
    private final char entity;
    CellState(char entity){
        this.entity=entity;
    }
    public char getEntity(){
        return entity;
    }
}

