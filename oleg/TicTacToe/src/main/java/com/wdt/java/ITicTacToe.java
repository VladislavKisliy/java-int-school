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
public interface ITicTacToe {
    List<CellState> nextStep(List<CellState> gameField);
    boolean hasNextStep(List<CellState> gameField);
    String getPlayerName();
}