/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.sample2;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Dreamesc
 */
public class Sample2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<String> arrList = new ArrayList<String>();
        ReadFile rf = new ReadFile("in.txt");
        WriteFile wf = new WriteFile("out.txt");
        rf.read(arrList);
        
        TreeSet<Double> set = new TreeSet<Double>();
        for (String string : arrList) {
            for (String strSplit : string.split("[\\s]")){
                set.add(Double.valueOf(strSplit));
            }            
        }
        Double db = Double.valueOf("33");//Double.valueOf(System.console().readLine());        
        List<String> outFile = new ArrayList<String>(arrList);
        outFile.add(set.floor(db).toString());
        outFile.add(set.ceiling(db).toString());        
        System.out.println(set.floor(db));
        System.out.println(set.ceiling(db));
        
        System.out.println(set);
        wf.write(outFile);        
    }
}
