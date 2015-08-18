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
    Integer i= 0 ;
//    public static int getGlobalValue(){
//        return i;
//    }
    public void incrementValue()
    {
        i++;
        System.out.println(i);
    }
    public void run() {
        this.incrementValue();
    }

     public static void main(String args[]) throws InterruptedException {
//        Thread testThread = new Thread(new ThreadsParallelRun());
//        Runnable testRun = ; 
        System.out.println("Hello, World. I'm main.");
        for (int z=0;z<10;z++)
        {
//            (new Thread(new ThreadsParallelRun())).start();
            Thread testThread = new Thread(new ThreadsParallelRun());
            testThread.start();
        }
        
//        System.out.println(String.valueOf(i));
     }
}
