/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Properties;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Oleg
 */
public class DataBaseIOTest {
    
    public DataBaseIOTest() {
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
     * Test of getDs method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testGetDs() {
        System.out.println("getDs");
        DataBaseIO instance = new DataBaseIO();
        DataSource expResult = null;
        DataSource result = instance.getDs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDs method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testSetDs() {
        System.out.println("setDs");
        DataSource ds = null;
        DataBaseIO instance = new DataBaseIO();
        instance.setDs(ds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readPropertiesFromDB method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testReadPropertiesFromDB() throws Exception {
        System.out.println("readPropertiesFromDB");
        String tableOwner = "";
        String tableName = "";
        DataBaseIO instance = new DataBaseIO();
        Properties expResult = null;
        Properties result = instance.readPropertiesFromDB(tableOwner, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDBproperties method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testGetDBproperties() {
        System.out.println("getDBproperties");
        String parameterFile = "";
        DataBaseIO instance = new DataBaseIO();
        Properties expResult = null;
        Properties result = instance.getDBproperties(parameterFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDS method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testGetDS() {
        System.out.println("getDS");
        Properties props = null;
        DataBaseIO instance = new DataBaseIO();
        DataSource expResult = null;
        DataSource result = instance.getDS(props);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertiesInDB method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testUpdatePropertiesInDB() {
        System.out.println("updatePropertiesInDB");
        Properties prop = null;
        String tableOwner = "";
        String tableName = "";
        DataBaseIO instance = new DataBaseIO();
        instance.updatePropertiesInDB(prop, tableOwner, tableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPropertiesInDB method, of class DataBaseIO.
     */
    @Test
    @Ignore
    public void testInsertPropertiesInDB() {
        System.out.println("insertPropertiesInDB");
        Properties prop = null;
        String tableOwner = "";
        String tableName = "";
        DataBaseIO instance = new DataBaseIO();
        instance.insertPropertiesInDB(prop, tableOwner, tableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
