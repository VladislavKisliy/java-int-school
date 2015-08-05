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
     * @param inList
     * @return
     */
    public static String[] getNeghbors(int inInt,TreeSet inList){
        String[] outResult = new String[2];
        double inConv=inInt*1.0;
        outResult[0]=String.valueOf(inList.floor(inConv));
        outResult[1]=String.valueOf(inList.higher(inConv));
        return outResult;
    }
}
