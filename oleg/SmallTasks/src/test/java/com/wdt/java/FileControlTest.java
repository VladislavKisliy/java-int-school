/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rustem
 */
public class FileControlTest extends TestCase {
    
    public FileControlTest(String testName) {
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
     * Test of write method, of class FileControl.
     */
    @Ignore
    @Test
    public void testWrite() {
        System.out.println("write");
        String fileName = "C:\\test\\SmallTasks\\pom.xml";
        String textToWrite = "Hello, World";
//        FileControl instance = new FileControl();
//        instance.write(fileName, textToWrite);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        //success("123").;
    }

    /**
     * Test of read method, of class FileControl.
     */
    @Ignore
    @Test
    public void testRead() {
        System.out.println("read");
        String fileName = "C:\\test\\SmallTasks\\pom.xml";
        FileControl instance = new FileControl();
        String expResult = "";
//        String result = instance.read(fileName);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of exists method, of class FileControl.
     */
    @Ignore
    @Test
    public void testExists() {
        System.out.println("exists");
        String fileName = "C:\\test\\SmallTasks\\pom.xml";
        FileControl instance = new FileControl();
        boolean expResult = false;
//        boolean result = instance.exists(fileName);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class FileControl.
     */
    @Ignore
    @Test
    public void testUpdate() {
        System.out.println("update");
        String fileName = "C:\\test\\SmallTasks\\pom.xml";
        String newText = "";
//        FileControl instance = new FileControl();
//        instance.update(fileName, newText);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
