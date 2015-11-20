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
import java.util.logging.Logger;

/**
 *
 * @author Oleg
 */
public class ParsParams {
    private static final Logger LOG = Logger.getLogger(ParsParams.class.getName());
    public Properties getPropertiesFromFile(String file){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream(file);
            prop.load(input);
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
    public void writePropertiesToFile(String filename, Properties prop){
	OutputStream output = null;
	try {
		output = new FileOutputStream(filename);
                prop.store(output, null);
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {output.close();} 
                        catch (IOException e) 
                            {System.err.println(e.getMessage());}
		}
	}
    }
}
