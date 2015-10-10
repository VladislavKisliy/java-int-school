/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java.ThreadsTask;

/**
 *
 * @author Oleg 
 */
public class ThreadsPrinting implements Runnable{
    public void run() {
        System.out.println("Hello, World! I'm thread!");
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello, World. I'm main.");
        Runnable testThread1= new ThreadsPrinting(); 
        Thread runThread= (new Thread(testThread1));
        runThread.start();
        runThread.join();
        System.out.println("Bye-bye, World. I'm main.");
    }

}
