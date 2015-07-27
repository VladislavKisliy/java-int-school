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
import static com.weigandtconsulting.javaschool.beans.CellState.TAC;
import static com.weigandtconsulting.javaschool.beans.CellState.TIC;
import static com.weigandtconsulting.javaschool.beans.CellState.TOE;
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
public class PlayerTest {
    
    private final CellState[] variant1 = {
            TAC, TAC, TAC,
            TOE, TOE, TOE,
            TOE, TOE, TOE};
    
    private final CellState[] variant2 = {
            TAC, TAC, TOE,
            TOE, TIC, TOE,
            TOE, TIC, TOE};
    
    private final CellState[] oneEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TAC};
    
    private final CellState[] twoEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TOE};
    
    private final CellState[] fullField = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TIC, TAC};
    
    public PlayerTest() {
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
     * Test of nextStep method, of class Player.
     */
    @Test
    public void testNextStep() {
        System.out.println("nextStep");
        List<CellState> gameField = null;
        Player instance = new Player(TAC);
        List<CellState> expResult = null;
        List<CellState> result = instance.nextStep(gameField);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasNextStep method, of class Player.
     */
    @Test
    public void testHasNextStep() {
        System.out.println("hasNextStep");
        Player instance = new Player(TAC);
        Assert.assertFalse("Winner situation", instance.hasNextStep(Arrays.asList(variant1)));
        Assert.assertTrue("Game in progress", instance.hasNextStep(Arrays.asList(variant2)));
        Assert.assertFalse("Fullfield situation", instance.hasNextStep(Arrays.asList(fullField)));
    }

    /**
     * Test of getPlayerName method, of class Player.
     */
    @Test
    public void testGetPlayerName() {
        Player instance = new Player(TIC);
        String expResult = "Megamind (V.K) X";
        String result = instance.getPlayerName();
        assertEquals(expResult, result);
    }

    /**
     * Test of score method, of class Player.
     */
    @Test
    public void testScore() {
        int depth = 0;
        assertEquals("Winner", 10, new Player(TAC).score(Arrays.asList(variant1), depth));
        assertEquals("Loser", -10, new Player(TIC).score(Arrays.asList(variant1), depth));
        assertEquals("Nothing happened TIC", 0, new Player(TIC).score(Arrays.asList(variant2), depth));
        assertEquals("Nothing happened TAC", 0, new Player(TAC).score(Arrays.asList(variant2), depth));
        assertEquals("Nothing happened full", 0, new Player(TIC).score(Arrays.asList(fullField), depth));
    }

    /**
     * Test of miniMax method, of class Player.
     */
    @Test
    public void testMiniMax() {
        int depth = 0;
        
        Player instance = new Player(TIC);
        List<CellState> expResult = Arrays.asList(fullField);
        List<CellState> result = instance.miniMax(Arrays.asList(fullField), depth);
        assertEquals(expResult, result);
        
        result = instance.miniMax(Arrays.asList(oneEmptySpace), depth);
        assertEquals("One empty space TIC", Arrays.asList(fullField), result);
        
        CellState[] twoEmptyResultOffense = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TIC};
        result = instance.miniMax(Arrays.asList(oneEmptySpace), depth);
        assertEquals("Two empty space Offense TIC", Arrays.asList(twoEmptyResultOffense), result);
        
        
        CellState[] twoEmptyResultDefense = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TAC};
        result = new Player(TAC).miniMax(Arrays.asList(oneEmptySpace), depth);
        assertEquals("Two empty space Defense TAC", Arrays.asList(twoEmptyResultDefense), result);
    }
    
}
