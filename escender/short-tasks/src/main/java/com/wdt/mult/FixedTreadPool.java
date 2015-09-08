/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.mult;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.*;

/**
 *
 * @author Dreamesc
 */
public class FixedTreadPool extends Thread {

    public static Queue<Integer> concurrentQueue = new ConcurrentLinkedQueue<Integer>();

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Start Main");
        List<Future> arrFutures = new LinkedList<Future>();
        ExecutorService pool = Executors.newFixedThreadPool(5);        
        for (int i = 0; i < 10; i++) {
            arrFutures.add(pool.submit(new FixedTreadPool()));
        }

        int i = 0;
        while (!arrFutures.isEmpty()) {
            i = (i + 1) % arrFutures.size();
            if (arrFutures.get(i).isDone()) {
                arrFutures.remove(i);
                System.out.println("End thread with sleep time:" + concurrentQueue.poll());
            };
        }

        pool.shutdown();
        System.out.println("End Main");
    }
    private Integer waitTime;

    public void run() {
        Random rn = new Random();
        waitTime = rn.nextInt(3000);
        System.out.println("Start thread with sleep time in millisecond - " + waitTime);
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(FixedTreadPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        concurrentQueue.add(waitTime);
    }
}
