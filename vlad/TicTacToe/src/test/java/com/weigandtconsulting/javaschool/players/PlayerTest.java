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
package com.weigandtconsulting.javaschool.players;

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
    
    private final CellState[] threeEmptySpace = {
            TIC, TOE, TIC,
            TAC, TOE, TAC,
            TAC, TOE, TAC};
    
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
        Player playerTic = new Player(TIC);
        List<CellState> gameField = Arrays.asList(oneEmptySpace);
        List<CellState> expResult = Arrays.asList(fullField);
        List<CellState> result = playerTic.nextStep(gameField);
        assertEquals(expResult, result);
        
        // Only for manual testing (random generator)
//        gameField = new GameFieldImpl().getNewField();
//        result = instance.nextStep(gameField);
//        assertEquals(expResult, result);
        
        CellState[] expTwoEmptyTic = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TIC};
        
        gameField = Arrays.asList(twoEmptySpace);
        result = playerTic.nextStep(gameField);
        assertEquals("Two empty space offense TIC", Arrays.asList(expTwoEmptyTic), result);
        
        CellState[] expTwoEmptyTac = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TAC};
        
        gameField = Arrays.asList(twoEmptySpace);
        result = new Player(TAC).nextStep(gameField);
        assertEquals("Two empty space defense TAC", Arrays.asList(expTwoEmptyTac), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextStepException() {
        List<CellState> gameField = null;
        Player instance = new Player(TAC);
        List<CellState> result = instance.nextStep(gameField);
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
        Player instance = new Player(TAC);
        assertEquals("Winner", 10, instance.score(Arrays.asList(variant1), depth, TAC));
        assertEquals("Loser", -10, instance.score(Arrays.asList(variant1), depth, TIC));
        assertEquals("Nothing happened TIC", 0, instance.score(Arrays.asList(variant2), depth, TIC));
        assertEquals("Nothing happened TAC", 0, instance.score(Arrays.asList(variant2), depth, TAC));
        assertEquals("Nothing happened full", 0, instance.score(Arrays.asList(fullField), depth, TIC));
    }

    /**
     * Test of miniMax method, of class Player.
     */
    @Test
    public void testMiniMaxSimple() {
        int depth = 0;
        
        Player instance = new Player(TIC);
        int result = instance.miniMax(Arrays.asList(fullField), depth);
        assertEquals(0, result);
        
        result = instance.miniMax(Arrays.asList(oneEmptySpace), depth);
        assertEquals("One empty space TIC", 7, result);
    }
    
    @Test
    public void testMiniMax() {
        int depth = 0;
        
        Player playerTic = new Player(TIC);
        int result = playerTic.miniMax(Arrays.asList(twoEmptySpace), depth);
        assertEquals("Two empty space offense TIC", 8, result);
        result = playerTic.miniMax(Arrays.asList(threeEmptySpace), depth);
        assertEquals("Three empty space offense TAC", 1, result);
        
        Player playerTac = new Player(TAC);
        result = playerTac.miniMax(Arrays.asList(twoEmptySpace), depth);
        assertEquals("Two empty space defense TAC", 8, result);
        result = playerTac.miniMax(Arrays.asList(threeEmptySpace), depth);
        assertEquals("Three empty space defense TAC", 4, result);
        
    }

    /**
     * Test of getSign method, of class Player.
     */
    @Test
    public void testGetSign() {
        CellState playerSign = TIC;
        Player instance = new Player(TAC);
        assertEquals(TIC, instance.getSign(playerSign, 1));
        assertEquals(TAC, instance.getSign(playerSign, 2));
        assertEquals(TIC, instance.getSign(playerSign, 3));
        assertEquals(TAC, instance.getSign(playerSign, 4));
    }
    
}
