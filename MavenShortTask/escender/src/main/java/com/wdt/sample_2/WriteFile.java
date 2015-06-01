/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.sample_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class WriteFile {

    private String fileName;
    
    public WriteFile(String fileName) {
        this.fileName = fileName;
    }

    public void write(List<String> arrList) {
        BufferedWriter bw = null;
        try {
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF8");
            //FileWriter fw = new OutputStreamWrteFileWriter(new File(xFile), "UTF-8");            
            bw = new BufferedWriter(fw);

            for (String line : arrList) {
                bw.write(line);
                bw.newLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
