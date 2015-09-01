/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.mult;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class FixedTreadPool extends Thread {
    
    private Integer waitTime;

    public FixedTreadPool(Integer waitTime) {
        this.waitTime = waitTime;
    }   
    
    public void run() {
        System.out.println("Start thread with sleep time in second - " + waitTime);
        try {
            TimeUnit.SECONDS.sleep(waitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(FixedTreadPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("End thread - " + waitTime);
    }
            
    public static void main (String args[]) throws InterruptedException {
        System.out.println("Start Main");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Random rn = new Random();
        for (int i = 0; i < 10; i++) {
            pool.submit(new FixedTreadPool(rn.nextInt(10)));
            //System.out.println();
        }
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdownNow();
        System.out.println("End Main");
    }
}
