/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * @author Oleg
 */
public class SmallTasks {
        public static void main(String[] args) {
        // TODO code application logic here
        String file="C:\\Users\\Rustem\\Desktop\\Oleg\\KONZUM\\дежурства\\correction_scripts_execution_procedure.txt";
        checkDuplicates(file);
        String doubleFile="D:\\doubles.xml";
        checkNeighbors(1000,doubleFile);
    }
    public static void checkDuplicates(String pathToFile){
        FileControl controlFile=new FileControl();
        String inStr=controlFile.read(pathToFile);
//        System.out.println("File:"+inStr);
        System.out.println("Duplicates:"+SearhDuplicates.duplicatesOrUniq(inStr,'D').toString());
        System.out.println("Unique:"+SearhDuplicates.duplicatesOrUniq(inStr,'U').toString());
    }
    public static void checkNeighbors(int inInt, String pathToFile){
        FileControl controlFile=new FileControl();
        String inStr=controlFile.read(pathToFile);
        TreeSet sequence=new TreeSet();
        String[] tempSplitted=(inStr.split("\\s+"));
        for (int i=0;i<tempSplitted.length;i++){
            sequence.add(Double.parseDouble(tempSplitted[i]));    
        }
        
        System.out.println("Here are neighbors: lower: " +SearchNeighbors.getNeghbors(inInt,sequence)[0]+" Higher: "+SearchNeighbors.getNeghbors(inInt,sequence)[1]);
    }
    public static String removeBadCharacters(String inString){
        String outString=inString.replaceAll("^A-Za-z0-9.", "");
        return outString;
    }
}

