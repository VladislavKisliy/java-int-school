/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleg
 */
public class ParsParams {
    private static final Logger LOG = Logger.getLogger(ParsParams.class.getName());
    public Properties getPropertiesFromFile(String file){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(file); ){
            prop.load(input);
	} catch (IOException ex) {
		LOG.log(Level.SEVERE, ex.getMessage());
	} 
        return prop;
    }
    public void writePropertiesToFile(String filename, Properties prop){
	try (OutputStream output = new FileOutputStream(filename);){
                prop.store(output, null);
	} catch (IOException io) {
                LOG.log(Level.SEVERE, io.getMessage());
	}
    }
}
