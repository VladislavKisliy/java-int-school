/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Oleg
 */
public class ThreadsExamplePool {

    public static int getNumOfThreads() {
        return numOfThreads;
    }
    private static int numOfThreads=20;
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
        ExecutorService executor = Executors.newFixedThreadPool(ThreadsExamplePool.getNumOfThreads());
        Integer result=0;
//        Set<Future<Integer>> set = new HashSet<Future<Integer>>();
        BlockingQueue threadQueue = new ArrayBlockingQueue(ThreadsExamplePool.getNumOfThreads());
        for (int i=0; i<ThreadsExamplePool.getNumOfThreads(); i++){
            ThreadCallable tempThread = new ThreadCallable(i);
            Future resultF = executor.submit(tempThread);
            threadQueue.add(resultF);
            //Future <Integer> future=executor.submit(tempThread);
//            set.add(future);
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
}
