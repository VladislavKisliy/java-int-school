/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.sample_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class ReadFile {
    
    private String fileName;
    
    public ReadFile(String fileName) {
        this.fileName = fileName;
    }
    
    public List<String> read(List<String> arrList) {        
        try {
            File f = new File(fileName);            
            FileReader fr = new FileReader(f);
            
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                arrList.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return arrList;
        }
            
    }
        
}
