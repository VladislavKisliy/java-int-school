/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author user
 */
public class ParsParams {
    private Properties getProperties(String file){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = getClass().getClassLoader().getResourceAsStream(file);
            if (input == null) {
		System.out.println("Sorry, unable to find " + file);
                    
	}
        prop.load(input);
        Enumeration<?> e = prop.propertyNames();
//	while (e.hasMoreElements()) {
//            String key = (String) e.nextElement();
//            String value = prop.getProperty(key);
//            System.out.println("Key : " + key + ", Value : " + value);
//	}
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
            if (input != null) {
		try {input.close();} 
                catch (IOException e) {e.printStackTrace();}
            }
	}
        return prop;
    }
    
}
