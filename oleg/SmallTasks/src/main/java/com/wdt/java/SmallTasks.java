/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

/**
 *
 * @author Oleg
 */
public class SmallTasks {
        public static void main(String[] args) {
        // TODO code application logic here
        String file="C:\\Users\\Rustem\\Desktop\\Oleg\\KONZUM\\дежурства\\correction_scripts_execution_procedure.txt";
        FileControl controlFile=new FileControl();
        String inStr=controlFile.read(file);
//        System.out.println("File:"+inStr);
        System.out.println("Duplicates:"+SearhDuplicates.duplicatesOrUniq(inStr,'D').toString());
        System.out.println("Unique:"+SearhDuplicates.duplicatesOrUniq(inStr,'U').toString());
    }
}
