/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Oleg
 */
public class AIClassTest extends TestCase {
    
    public AIClassTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of nextStepAI method, of class AIClass.
     */
    public void testNextStepAI() {
        System.out.println("nextStepAI");
        List<CellState> gameField = null;
        AIClass instance = new AIClass();
        List<CellState> expResult = null;
        List<CellState> result = instance.nextStepAI(gameField);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariantsToCheck method, of class AIClass.
     */
    public void testGetVariantsToCheck() {
        System.out.println("getVariantsToCheck");
        int dimention = 2;
        List<Integer> expResult = new ArrayList <Integer> ();
        expResult.add(1);
        expResult.add(2); //.addAll(1,2,3,4,1,3,2,4,1,4,2,3);
        expResult.add(3);
        expResult.add(4);
        expResult.add(1);
        expResult.add(3);
        expResult.add(2);
        expResult.add(4);
        expResult.add(1);
        expResult.add(4);
        expResult.add(2);
        expResult.add(3);
        List<Integer> result = AIClass.getVariantsToCheck(dimention);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
