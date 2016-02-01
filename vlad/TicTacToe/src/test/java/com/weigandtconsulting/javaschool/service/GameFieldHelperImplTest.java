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

import com.weigandtconsulting.javaschool.beans.CellState;
import static com.weigandtconsulting.javaschool.beans.CellState.*;
import com.weigandtconsulting.javaschool.beans.Game;
import static com.weigandtconsulting.javaschool.service.GameFieldHelperImpl.CELL_AMOUNT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlad
 */
public class GameFieldHelperImplTest {

    private final CellState[] variant1 = {
            TAC, TAC, TAC,
            TOE, TOE, TOE,
            TOE, TOE, TOE};
    
    private final CellState[] variant2 = {
            TAC, TIC, TAC,
            TOE, TAC, TIC,
            TOE, TOE, TAC};
        
    private final CellState[] variant3 = {
            TAC, TIC, TAC,
            TAC, TAC, TAC,
            TIC, TOE, TIC};
        
    private final CellState[] variant4 = {
            TOE, TIC, TAC,
            TAC, TIC, TAC,
            TIC, TAC, TAC};
    
    private final CellState[] variant5 = {
            TOE, TIC, TAC,
            TOE, TIC, TAC,
            TIC, TAC, TOE};
    
    private final CellState[] fullField = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TIC, TAC};
    
    public GameFieldHelperImplTest() {
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
     * Test of isWinner method, of class GameFieldImpl.
     */
    @Test
    public void testIsWinner() {
        CellState playerTAC = CellState.TAC;

        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        Boolean result = instance.isWinner(instance.getNewField(), playerTAC);
        Assert.assertFalse("Empty field", result);
        // Winner section
        Assert.assertTrue("Variant1", instance.isWinner(Arrays.asList(variant1), playerTAC));
        Assert.assertTrue("Variant2", instance.isWinner(Arrays.asList(variant2), playerTAC));
        Assert.assertTrue("Variant3", instance.isWinner(Arrays.asList(variant3), playerTAC));
        Assert.assertTrue("Variant4", instance.isWinner(Arrays.asList(variant4), playerTAC));
        
        // Loser section
        CellState playerTIC = CellState.TIC;
        Assert.assertFalse("Variant1 inverse", instance.isWinner(Arrays.asList(variant1), playerTIC));
        Assert.assertFalse("Variant2 inverse", instance.isWinner(Arrays.asList(variant2), playerTIC));
        Assert.assertFalse("Variant3 inverse", instance.isWinner(Arrays.asList(variant3), playerTIC));
        Assert.assertFalse("Variant4 inverse", instance.isWinner(Arrays.asList(variant4), playerTIC));
    }

    /**
     * Test of getNewField method, of class GameFieldImpl.
     */
    @Test
    public void testGetNewField() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        List<CellState> expResult = new ArrayList<>(CELL_AMOUNT);
        for (int i = 0; i < CELL_AMOUNT; i++) {
            expResult.add(CellState.TOE);
        }
        List<CellState> result = instance.getNewField();
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class GameFieldImpl.
     */
    @Test
    public void testIsGameOver() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        Assert.assertTrue("Winner situation 1", instance.isGameOver(Arrays.asList(variant1)));
        Assert.assertTrue("Winner situation 2", instance.isGameOver(Arrays.asList(variant2)));
        Assert.assertFalse("Nothing", instance.isGameOver(Arrays.asList(variant5)));
        Assert.assertTrue("Fullfield situation", instance.isGameOver(Arrays.asList(fullField)));
    }

    /**
     * Test of getAvailableMoves method, of class GameFieldImpl.
     */
    @Test
    public void testGetAvailableMoves() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        List<Integer> result = instance.getAvailableMoves(Arrays.asList(fullField));
        assertEquals("Full length", 0, result.size());
        
        Integer[] expectedVariant1 = {3,4,5,6,7,8};
        result = instance.getAvailableMoves(Arrays.asList(variant1));
        assertEquals("Variant1 length", expectedVariant1.length, result.size());
        assertEquals("Variant1 list", Arrays.asList(expectedVariant1), result);
        
        Integer[] expectedVariant2 = {3,6,7};
        result = instance.getAvailableMoves(Arrays.asList(variant2));
        assertEquals("Variant2 length", expectedVariant2.length, result.size());
        assertEquals("Variant2 list", Arrays.asList(expectedVariant2), result);
        
        Integer[] expectedVariant3 = {7};
        result = instance.getAvailableMoves(Arrays.asList(variant3));
        assertEquals("Variant3 length", expectedVariant3.length, result.size());
        assertEquals("Variant3 list", Arrays.asList(expectedVariant3), result);
        
        Integer[] expectedVariant4 = {0};
        result = instance.getAvailableMoves(Arrays.asList(variant4));
        assertEquals("Variant4 length", expectedVariant4.length, result.size());
        assertEquals("Variant4 list", Arrays.asList(expectedVariant4), result);
    }

    /**
     * Test of doStep method, of class GameFieldImpl.
     */
    @Test
    public void testDoStep() {
        
        CellState[] expResult = {
            TAC, TAC, TAC,
            TOE, TIC, TOE,
            TOE, TOE, TOE};
        
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        List<CellState> gameField = Arrays.asList(variant1);
        List<CellState> result = instance.doStep(gameField, CellState.TIC, 4);
        assertNotEquals(gameField, result);
        assertEquals("Insertion error", Arrays.asList(expResult), result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDoStepException() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        instance.doStep(Arrays.asList(fullField), CellState.TIC, 6);
    }

    /**
     * Test of isFieldEmpty method, of class GameFieldHelperImpl.
     */
    @Test
    public void testIsFieldEmpty() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        assertFalse("Full field", instance.isFieldEmpty(Arrays.asList(fullField)));
        assertFalse("Variant1", instance.isFieldEmpty(Arrays.asList(variant1)));
        
        assertTrue("Empty field", instance.isFieldEmpty(instance.getNewField()));
    }

    /**
     * Test of analyzeGame method, of class GameFieldHelperImpl.
     */
    @Test
    public void testAnalyzeGame() {
        GameFieldHelperImpl instance = new GameFieldHelperImpl();
        Game result = instance.analyzeGame(Arrays.asList(fullField));
        assertEquals("FullField. game over", Game.State.OVER, result.getState());
        assertEquals("FullField. draw", Game.Result.DRAW, result.getResult());
        assertEquals("FullField. winner", CellState.TOE, result.getWinnerSign());
        
        result = instance.analyzeGame(Arrays.asList(variant1));
        assertEquals("variant1. game over", Game.State.OVER, result.getState());
        assertEquals("variant1. win", Game.Result.WIN, result.getResult());
        assertEquals("variant1. winner", CellState.TAC, result.getWinnerSign());
        
        result = instance.analyzeGame(Arrays.asList(variant2));
        assertEquals("variant2. game over", Game.State.OVER, result.getState());
        assertEquals("variant2. win", Game.Result.WIN, result.getResult());
        assertEquals("variant2. winner", CellState.TAC, result.getWinnerSign());
        
        result = instance.analyzeGame(instance.getNewField());
        assertEquals("NewField. game over", Game.State.START, result.getState());
        assertEquals("NewField. win", Game.Result.UKNOWN, result.getResult());
        assertEquals("NewField. winner", CellState.TOE, result.getWinnerSign());
        
        result = instance.analyzeGame(Arrays.asList(variant5));
        assertEquals("variant5. game over", Game.State.CONTINUE, result.getState());
        assertEquals("variant5. win", Game.Result.UKNOWN, result.getResult());
        assertEquals("variant5. winner", CellState.TOE, result.getWinnerSign());
    }
}
