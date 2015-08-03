/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import com.wdt.java.SearhDuplicates;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
//import org.junit.Ignore;
//import org.junit.Test;

/**
 *
 * @author Rustem
 */
public class SearhDuplicatesTest extends TestCase {
    
    public SearhDuplicatesTest(String testName) {
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
     * Test of duplicatesOrUniq method, of class SearhDuplicates.
     */
//    @Ignore
    public void testDuplicatesOrUniq() {
        System.out.println("duplicatesOrUniq");
        String inStr = "123 123";
        char resultInd = 'D';
        Set<String> expResult =new HashSet() ;
        expResult.add("123");
        Set<String> result = SearhDuplicates.duplicatesOrUniq(inStr, resultInd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
