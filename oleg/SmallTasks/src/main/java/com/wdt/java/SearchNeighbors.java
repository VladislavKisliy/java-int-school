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
    public static double[] getNeghbors(int inInt,TreeSet inList){
        double[] outResult = new double[2];
        outResult[0]=(double) inList.floor(inInt);
        outResult[1]=(double) inList.higher(inInt);
        return outResult;
    }
}
