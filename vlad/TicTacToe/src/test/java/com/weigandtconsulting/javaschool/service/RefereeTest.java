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
import static com.weigandtconsulting.javaschool.beans.CellState.TAC;
import static com.weigandtconsulting.javaschool.beans.CellState.TIC;
import static com.weigandtconsulting.javaschool.beans.CellState.TOE;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlad
 */
public class RefereeTest {
    
    private final CellState[] oneEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TAC};
    
    private final CellState[] twoEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TOE};
    
    public RefereeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isCorrectTurn method, of class Referee.
     */
    @Test
    public void testIsCorrectTurn() {
        Referee instance = new Referee(null, null, null);
        List<CellState> gameFieldBefore = Arrays.asList(oneEmptySpace);
        List<CellState> gameFieldAfter = Arrays.asList(oneEmptySpace);
        assertFalse("The same fields", instance.isCorrectTurn(gameFieldBefore, gameFieldAfter));
        
        gameFieldBefore = Arrays.asList(oneEmptySpace);
        gameFieldAfter = Arrays.asList(twoEmptySpace);
        assertFalse("Reverse", instance.isCorrectTurn(gameFieldBefore, gameFieldAfter));
        
        gameFieldBefore = Arrays.asList(twoEmptySpace);
        gameFieldAfter = Arrays.asList(oneEmptySpace);
        assertTrue("Right turn", instance.isCorrectTurn(gameFieldBefore, gameFieldAfter));
    }
}
