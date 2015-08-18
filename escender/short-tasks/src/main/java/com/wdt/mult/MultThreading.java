/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.mult;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dreamesc
 */
public class MultThreading extends Thread {
    private static Integer commonIterator;
    
    private final static int THREADS_AMOUNT = 10;

    public int getCommonIterator() {
        return commonIterator;
    }

    public void setCommonIterator(Integer commonIterator) {
        MultThreading.commonIterator = commonIterator;
    }
    
    @Override
    public void run() {
        int localIterator = getCommonIterator();
        localIterator++;
        setCommonIterator(localIterator);
        System.out.println(getName()+".Iterator:"+commonIterator);
        
    }
    
    public void startThreads() throws InterruptedException {
        System.out.println("Start");
        List<Thread> arrThread = new ArrayList<Thread>();
        for (int i = 0; i < THREADS_AMOUNT; i++) {
            arrThread.add(new MultThreading());
        }
        for (Thread currentThread : arrThread) {
            currentThread.start();
        }
        System.out.println("End1 with Iterator:" + commonIterator);
        arrThread.get(THREADS_AMOUNT-1).join();
        System.out.println("End2 with Iterator:" + commonIterator);
    }

    
    public static void main(String[] args) throws InterruptedException {
        commonIterator = 0;
        new MultThreading().startThreads();
    }
    
}
