/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;

import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 *
 * @author Oleg 
 */

public class ThreadsParallelRun implements Runnable{
    public void incrementValue()
    {
        GlobalVariables.GLOBAL_I++;
        System.out.println(GlobalVariables.GLOBAL_I);
    }
    public void run() {
        incrementValue();
    }

     public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello, World. I'm main.");
        for (int z=0;z<10;z++)
        {
            Thread testThread = new Thread(new ThreadsParallelRun());
            testThread.start();
        }
     }
}
