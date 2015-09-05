/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Oleg
 */
public class ThreadsParallelRun implements Runnable {

    public void  incrementValue() {
        int tempI = GlobalVariables.getGLOBAL_I();
        System.out.println(tempI);
        tempI++;
        GlobalVariables.setGLOBAL_I(tempI);
//        System.out.println(tempI);
    }

    public void run() {
        GlobalVariables.increment();
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello, World. I'm main.");
        for (int z = 0; z < 100; z++) {
            Thread testThread = new Thread(new ThreadsParallelRun());
            testThread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("I'm done. I'm:"+GlobalVariables.getGLOBAL_I());
    }
}
