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

import java.util.concurrent.Exchanger;

/**
 *
 * @author vlad
 */
public class ExchangerExample {

    Exchanger<String> exchanger = new Exchanger<>();

    private class Producer implements Runnable {

        private String queue;

        @Override
        public void run() {
            try {
                //create tasks & fill the queue
                //exchange the full queue for a empty queue with Consumer
                queue = exchanger.exchange("Ready Queue");
                System.out.println(Thread.currentThread().getName() + " now has " + queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Consumer implements Runnable {

        private String queue;

        @Override
        public void run() {
            try {
                //do procesing & empty the queue
                //exchange the empty queue for a full queue with Producer
                queue = exchanger.exchange("Empty Queue");
                System.out.println(Thread.currentThread().getName() + " now has " + queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        new Thread(new Producer(), "Producer").start();
        new Thread(new Consumer(), "Consumer").start();
    }

    public static void main(String[] args) {
        new ExchangerExample().start();
    }

}
