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
public class CoffeeShop {

    public static void main(String[] s) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Waiter waiter0 = new Waiter(0);
        Waiter waiter1 = new Waiter(1);
        Waiter waiter2 = new Waiter(2);
        coffeeMachine.start();
        waiter0.start();
        waiter1.start();
        waiter2.start();
    }

}
