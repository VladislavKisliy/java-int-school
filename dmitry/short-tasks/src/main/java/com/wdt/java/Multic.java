package com.wdt.java;

import java.util.ArrayList;
import java.util.List;

public class Multic extends Thread{
	private static int var = 0;
	
        @Override
	public void run(){
		System.out.println("Thread has been started");
		this.incVar();
	}

	public void incVar(){
		var++;
	}		
	
	public void printVar(){
		System.out.println("var = " + var);
	}
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println("App has been started");
		List<Multic> threadList  = new ArrayList<Multic>();
		for (int i=0; i<10; i++){
			threadList.add(new Multic());
			threadList.get(i).start();
		}
		threadList.get(0).printVar();
		System.out.println("App is finished");
	}
}
