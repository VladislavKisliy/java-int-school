/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.TreeSet;
import junit.framework.TestCase;

/**
 *
 * @author Oleg
 */
public class SearchNeighborsTest extends TestCase {
    
    public SearchNeighborsTest(String testName) {
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
     * Test of getNeghbors method, of class SearchNeighbors.
     */
    public void testGetNeghbors() {
        System.out.println("getNeghbors");
        double inInt = 0.0;
        TreeSet inList = null;
        String[] expResult = null;
        String[] result = SearchNeighbors.getNeghbors(inInt, inList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
