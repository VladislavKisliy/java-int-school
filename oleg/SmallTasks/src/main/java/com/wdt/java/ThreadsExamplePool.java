/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
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
    public static void main(String[] argc) throws InterruptedException, ExecutionException {    
        ExecutorService executor = Executors.newFixedThreadPool(ThreadsExamplePool.getNumOfThreads());
        Integer result=0;
        BlockingQueue threadQueue = new ArrayBlockingQueue(ThreadsExamplePool.getNumOfThreads());
        for (int i=0; i<ThreadsExamplePool.getNumOfThreads(); i++){
            ThreadCallable tempThread = new ThreadCallable(i);
            Future resultF = executor.submit(tempThread);
            threadQueue.add(resultF);
            System.out.println("Thread "+i+" submitted.");
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        while ( ! threadQueue.isEmpty()) {
            int threadNum = threadQueue.size();
            Future fObject = (Future) threadQueue.poll();
            System.out.println("Thread "+threadNum+" returned value: "+(Integer) fObject.get());
        }
        System.out.println("Finished all threads");
    }
}
