/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ThreadsExamplePool implements Callable {
//    public void run() {
//        Random rand = new Random();
//        try {
//            int timeToSleep=rand.nextInt(20);
//            TimeUnit.SECONDS.sleep(timeToSleep);
//            System.out.println("Sleeping for "+timeToSleep +" secomds");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ThreadsExamplePool.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] argc) {
        
        ExecutorService executor = Executors.newFixedThreadPool(20);
        Integer result=0;
        Set<Future<Integer>> set = new HashSet<Future<Integer>>();
        for (int i=0;i<20;i++){
            ThreadsExamplePool tempThread = new ThreadsExamplePool();
            Future <Integer> future=executor.submit(tempThread);
            set.add(future);
//            try {
//                result =future.get();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ThreadsExamplePool.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ExecutionException ex) {
//                Logger.getLogger(ThreadsExamplePool.class.getName()).log(Level.SEVERE, null, ex);
//            }
            System.out.println("Thread "+i+" submitted.");
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        int timeToSleep=rand.nextInt(20);
        try {
            TimeUnit.SECONDS.sleep(timeToSleep);
            System.out.println("Sleeping for "+timeToSleep +" secomds");
            return timeToSleep;
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadsExamplePool.class.getName()).log(Level.SEVERE, null, ex);
        }
       return timeToSleep;
    }
    
}
