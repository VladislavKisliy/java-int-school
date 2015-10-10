/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java.ThreadsTask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleg 
 */
public class ThreadCallable implements Callable  {
    int threadNum; 
    public ThreadCallable(int num){
        this.threadNum=num;
    }
    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        int timeToSleep=rand.nextInt(20);
        try {
            TimeUnit.SECONDS.sleep(timeToSleep);
//            System.out.println("Thread "+threadNum+". Sleeping for "+timeToSleep +" seconds.");
            return timeToSleep;
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadsExamplePool.class.getName()).log(Level.SEVERE, null, ex);
        }
       return timeToSleep;
    }

}
