package com.wdt.java;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * Created by ibeketov on 14/08/15.
 */
public class multi extends Thread{
private static int intVar = 0;

    public void incnementIntVar() {
        intVar++;
    }

    public void printIntVar(){
        out.println(intVar);
    }


    public void run() {
            out.println("Run new thred");
            this.incnementIntVar();
            this.printIntVar();
        }

    public static void main(String[] args) throws InterruptedException {
        out.println("Hello from a main!");
        List<multi> threadList  = new ArrayList<multi>();
        for (int i=0; i<10; i++){
            threadList.add(new multi());
            threadList.get(i).start();
        }
        threadList.get(0).printIntVar();
        out.println("Bye-bye from a main!");

    }

}
