/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import junit.framework.TestCase;

/**
 *
 * @author Oleg
 */
public class CellStateTest extends TestCase {
    
    public CellStateTest(String testName) {
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
     * Test of values method, of class CellState.
     */
    public void testValues() {
        System.out.println("values");
        CellState[] expResult={CellState.ZERO,CellState.CROSS,CellState.NONE};
        CellState[] result = CellState.values();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class CellState.
     */
    public void testValueOf() {
        System.out.println("valueOf ZERO");
        String name = "ZERO";
        CellState expResult = CellState.ZERO;
        CellState result = CellState.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEntity method, of class CellState.
     */
    public void testGetEntity() {
        System.out.println("getEntity");
        CellState instance=CellState.NONE;
        char expResult = ' ';
        char result = instance.getEntity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
