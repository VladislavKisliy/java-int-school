/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rustem
 */
public class SearhDuplicates {
    public static Set<String> duplicatesOrUniq(String inStr, char resultInd){
        List<String> strSplitted=Arrays.asList(inStr.split("\\s+"));
        Set<String> duplucates=new HashSet();
        Set<String> unique=new HashSet();
        Set <String> temp =new HashSet();
        for (String iterStr : strSplitted){
            if (!unique.add(iterStr)){
                duplucates.add(iterStr);
            }
        }
        if(resultInd=='D'){
            return duplucates; 
        }else if(resultInd=='U') {
            return unique;
        }else
            temp.add("ERROR: incorrect option. Please use [U, D]");
            return temp;
        
    }
}
