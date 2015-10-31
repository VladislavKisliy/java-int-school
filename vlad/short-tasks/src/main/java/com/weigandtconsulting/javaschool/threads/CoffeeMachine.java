/*
 * Copyright (C) 2015 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.threads;

/**
 *
 * @author vlad
 */
public class CoffeeMachine extends Thread {

    static String coffeeMade = null;
    static final Object lock = new Object();
    private static int coffeeNumber = 1;

    void makeCoffee() {
        synchronized (CoffeeMachine.lock) {
            if (coffeeMade != null) {

                try {
                    System.out.println("Coffee S machine: Waiting for waiter notification to deliver the coffee");
                    CoffeeMachine.lock.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            coffeeMade = "Coffee No. " + coffeeNumber++;
            System.out.println("Coffee machine says: Made " + coffeeMade);
            // once coffee is ready, notify the waiter to pick it up
            System.out.println("Coffee machine: Notifying waiter to pick the coffee ");
            CoffeeMachine.lock.notifyAll();
            
        }
    }

    public void run() {
        while (true) {
            makeCoffee();
            System.out.println("Coffee machine: Making another coffee now.");
            for (int i = 0; i < 5; i++) {
                try {
                    
// simulate the time taken to make a coffee by calling sleep method
                    Thread.sleep(1000);
                    System.out.print(".");
                } catch (InterruptedException ie) {
// its okay to ignore this exception
// since we're not using thread interrupt mechanism
                    ie.printStackTrace();
                }

            }
            System.out.println("");
        }
    }

//    public static void main(String[] args) {
//        CoffeeMachine coffeeMachine0 = new CoffeeMachine();
//        CoffeeMachine coffeeMachine1 = new CoffeeMachine();
//        CoffeeMachine coffeeMachine2 = new CoffeeMachine();
//        CoffeeMachine coffeeMachine3 = new CoffeeMachine();
//        coffeeMachine0.start();
//        coffeeMachine1.start();
//        coffeeMachine2.start();
//        coffeeMachine3.start();
//    }
}
