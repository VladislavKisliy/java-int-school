/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.TreeSet;
import junit.framework.TestCase;
import org.junit.Test;

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
    @Test
    public void testGetNeghbors() {
        System.out.println("getNeghbors");
        int inInt = 11;
        TreeSet inList =new TreeSet();
        for (double i=-100.0;i<=100.0;i+=0.1){
            inList.add(Math.round(i * 10.0)/10.0);
        }
        String[] expResult=new String[2];
        expResult[0]="10.9";
        expResult[1]="11.1";
        String[] result = SearchNeighbors.getNeghbors(inInt, inList);
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
    }
    
}
