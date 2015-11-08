/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author Oleg
 */
public class ControlTool {
    public static void main(String[] argv){
        ParsParams pp = new ParsParams();
        System.out.println(argv[0]);
        Properties prop = pp.getPropertiesFromFile(argv [0]);
        Enumeration<?> e = prop.propertyNames();
	while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            System.out.println("key: "+key+" value: "+value);
        }
    }
}
