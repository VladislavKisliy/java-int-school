package com.wdt.java;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class MyFork extends RecursiveTask<Long> {
    private List<Integer> workArray = new ArrayList<Integer>();
    private static final int checkForSplit = 1000000;

    MyFork(List<Integer> workArray) {
        this.workArray = workArray;
    }

    protected Long compute() {
    	long result = 0;
    	if (workArray.size() > checkForSplit){
    		System.out.println("Splitting " + workArray.size() + " values");
            List<MyFork> subtasks = new ArrayList<MyFork>();
            subtasks.addAll(createSubtasks());

            for(MyFork subtask : subtasks){
                subtask.fork();
            }

            for(MyFork subtask : subtasks) {
                result += subtask.join();
            }
            return result;    		
    	}else{
    		System.out.println("Working for " + workArray.size() + " values");
    		for (int i = 0; i < workArray.size(); i++){
    			result += workArray.get(i);
    		}
    		return result;
    	}

    }

    private List<MyFork> createSubtasks() {
        List<MyFork> subtasks = new ArrayList<MyFork>();
        
        int divInd = workArray.size()/2;
        MyFork subtask1 = new MyFork(workArray.subList(0, divInd));
        MyFork subtask2 = new MyFork(workArray.subList(divInd, workArray.size()));
        
        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
    
    public static void main(String[] args) {
				
		//fill data
    	List<Integer> workArray = new ArrayList<Integer>();
		for (int i = 0; i < 10000000; i++){
			workArray.add(i);
		}
		
		Date date= new Date();
		System.out.println("Started at " + new Timestamp(date.getTime()));
		
		//run fork
		MyFork myFork = new MyFork(workArray);
        long res = new ForkJoinPool().invoke(myFork);
        System.out.println("Result = " + res);
        
        date = new Date();
        System.out.println("Finished at " + new Timestamp(date.getTime()));        
    }
    
}