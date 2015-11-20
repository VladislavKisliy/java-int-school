/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oleg
 */
public class ParsParamsTest {
    
    public ParsParamsTest() {
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
     * Test of getPropertiesFromFile method, of class ParsParams.
     */
    @Test
    public void testGetPropertiesFromFile() {
        System.out.println("getPropertiesFromFile");
        String file = "";
        ParsParams instance = new ParsParams();
        Properties expResult = null;
        Properties result = instance.getPropertiesFromFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writePropertiesToFile method, of class ParsParams.
     */
    @Test
    public void testWritePropertiesToFile() {
        System.out.println("writePropertiesToFile");
        String filename = "";
        Properties prop = null;
        ParsParams instance = new ParsParams();
        instance.writePropertiesToFile(filename, prop);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
