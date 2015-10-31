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

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class Waiter extends Thread {

    private final Integer number;

    public Waiter(Integer number) {
        this.number = number;
    }
    
    public void getCoffee() {
        System.out.println("Waiter"+number+": Try to press button on the cofee machine");
        synchronized (CoffeeMachine.lock) {
            System.out.println("Waiter"+number+": Pressed button on the cofee machine");
            if (CoffeeMachine.coffeeMade == null) {
                try {
                // wait till the CoffeeMachine says (notifies) that
                    // coffee is ready
                    System.out.println(
                            "Waiter "+number+": Will get orders till coffee machine notifies me ");
                    CoffeeMachine.lock.wait();
                } catch (InterruptedException ie) {
// its okay to ignore this exception
// since we're not using thread interrupt mechanism
                    ie.printStackTrace();
                }
            }
            System.out.println("Waiter"+number+": Delivering " + CoffeeMachine.coffeeMade);
            CoffeeMachine.coffeeMade = null;
            // ask (notify) the coffee machine to prepare the next coffee
            CoffeeMachine.lock.notifyAll();
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException ex) {
                Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Waiter"+number+": Notifying coffee machine to make another one");
        }
    }

    public void run() {
// keep going till the user presses ctrl-C and terminates the program
        while (true) {
            getCoffee();
        }
    }

}
