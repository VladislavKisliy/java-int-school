/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.learn.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 *
 * @author Oleg
 */
public class FileControl implements FileMaintanence{
    public void    write(String fileName, String textToWrite){
        
    }

    /**
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public String  read(String fileName) {
        String tempString;
        StringBuilder readString = new StringBuilder();
        try{
        FileReader reader=new FileReader(fileName);
        BufferedReader in = new BufferedReader(reader);
        if(exists(fileName)){
//            System.out.println("I exist! (c) File");
            while((tempString=in.readLine())!=null){
                readString.append(tempString).append("\n");
            }
        }
        in.close();
        return readString.toString();
        } catch(IOException e) {
        throw new RuntimeException(e);
        }
    }
    
    /**
     *
     * @param fileName
     * @return
     */
    public boolean exists(String fileName){
        File file = new File(fileName);
            if (file.exists()){
               return true;
            }
        return false;
    }
    public void    update(String fileName, String newText){
        
    }
    
}
