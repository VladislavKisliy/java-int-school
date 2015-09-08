package com.wdt.java;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Executant implements Runnable{
	private int waitTime = 0;
	Queue<Integer> queue;
	
	Executant(Queue<Integer> queue){
		Random rand = new Random();
		this.waitTime = rand.nextInt(20);
		this.queue = queue;
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
