/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;

import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Oleg 
 */
public class SearchNeighbors {

    /**
     *
     * @param inInt
     * @return
     */
    public static String[] getNeghbors(double inInt,TreeSet inList){
        String[] outResult = new String[2];
        outResult[0]=String.valueOf(inList.floor(inInt));
        outResult[1]=String.valueOf(inList.higher(inInt));
        return outResult;
    }
}
