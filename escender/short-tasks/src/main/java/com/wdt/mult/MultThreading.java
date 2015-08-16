/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.mult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dreamesc
 */
public class MultThreading extends Thread {
    private static Integer commonIterator;
    
    public void run() {
        commonIterator++;
        System.out.println("Iterator:"+commonIterator);
    }
    
    public static void main(String[] args) {                
        System.out.println("Start");
        commonIterator = 0;
        List<Thread> arrThread= new ArrayList<Thread>();
        for (int i=0; i<10; i++) {
            arrThread.add(new MultThreading());
        }
        for (int i=0; i<10; i++) {
            arrThread.get(i).start();
        }
        System.out.println("End with Iterator:"+commonIterator);
    }
    
}
