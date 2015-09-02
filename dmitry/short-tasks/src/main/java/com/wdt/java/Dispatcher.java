package package com.wdt.java;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
	private static ExecutorService pool;
	private static final int poolSize = 5;
	private static final int numberOfThreads = 10;
	private static Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	
	static class Executant implements Runnable{
		private int waitTime;
		
		Executant (int waitTime){
			this.waitTime = waitTime;
		}
		
		public void run(){
			try {
				TimeUnit.SECONDS.sleep(waitTime);
			} catch (InterruptedException e) {
				System.out.println("Thread was interrupted by pool shutdown");
				e.printStackTrace();
			}
			queue.add(waitTime);			
		}
	}

    public static void main(String[] args){
    	System.out.println("Main");
    	pool = Executors.newFixedThreadPool(poolSize);
    	Random rand = new Random();
    	
    	//starting threads through pool
    	for(int i = 0; i < numberOfThreads; i++){
    		System.out.println("Thread " + i + " has been started.");
    		pool.execute(new Executant(rand.nextInt(20)));
    	}
    	
    	//waiting for all threads to be finished
    	int counter = 0;
    	while (counter < numberOfThreads){
    		if (!queue.isEmpty()){
    			System.out.println("Thread was finished. Wait time = " + queue.poll());    			
    			counter++;
    		}
    	}
    	
    	//closing pool
    	/*try {
			pool.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} */
    	pool.shutdown(); 
    	try {
			if (!pool.awaitTermination(1, TimeUnit.SECONDS)){
				pool.shutdownNow();
			}
		} catch (InterruptedException e) {
			pool.shutdownNow();
			e.printStackTrace();
		} 
	    System.out.println("The end.");
    }
	
}
