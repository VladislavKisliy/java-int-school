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
public class GlobalVariables {
    private static Integer globalI=0;

    public synchronized static Integer getGLOBAL_I() {
        return globalI;
    }
    public synchronized static void increment () {
        GlobalVariables.globalI++;
        
    }
    public synchronized static void setGLOBAL_I(Integer GLOBAL_I) {
        GlobalVariables.globalI = GLOBAL_I;
        
    }
    
}
