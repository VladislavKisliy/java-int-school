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
import static com.weigandtconsulting.javaschool.service.GameFieldImpl.CELL_AMOUNT;
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
public class GameFieldImplTest {

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
    
    public GameFieldImplTest() {
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

        GameFieldImpl instance = new GameFieldImpl();
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
        GameFieldImpl instance = new GameFieldImpl();
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
        GameFieldImpl instance = new GameFieldImpl();
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
        GameFieldImpl instance = new GameFieldImpl();
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
        
        GameFieldImpl instance = new GameFieldImpl();
        List<CellState> result = instance.doStep(Arrays.asList(variant1), CellState.TIC, 4);
        assertEquals("Insertion error", Arrays.asList(expResult), result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDoStepException() {
        GameFieldImpl instance = new GameFieldImpl();
        instance.doStep(Arrays.asList(fullField), CellState.TIC, 6);
    }
}
