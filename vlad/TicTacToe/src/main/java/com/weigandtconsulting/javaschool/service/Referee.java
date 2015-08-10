/*
 * Copyright (C) 2015 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.service;

import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;

/**
 *
 * @author vlad
 */
public class Referee {
    
    private final TicTacToe playerTic;
    private final TicTacToe playerTac;

    public Referee(TicTacToe playerTic, TicTacToe playerTac) {
        this.playerTic = playerTic;
        this.playerTac = playerTac;
    }
    
    public void startGame(CellState startSign) {
        
    }
    
    
    
}
