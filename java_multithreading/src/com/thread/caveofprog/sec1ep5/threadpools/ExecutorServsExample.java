package com.thread.caveofprog.sec1ep5.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServsExample {
	
	public static void main(String[] args) {
		System.out.println("Start Program...");

		long startTime = System.currentTimeMillis();
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<6; i++) {
			executor.submit(new Process(i));
			
		}

		
		executor.shutdown();

		System.out.println("All tasks submitted...");
		
		try {
			executor.awaitTermination(20000, TimeUnit.MILLISECONDS);
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		System.out.println("All tasks completed...");
		
		long endTime = System.currentTimeMillis();
		//System.out.println("End Time: " + endTime);
		
		System.out.println("Time taken: " + (endTime-startTime));
	}

}

class Process implements Runnable {
	
	private int id;
	
	public Process(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting id: " +  id);
		
		try {
			Thread.sleep(1000);
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		System.out.println("Completed id: " + id);
	}
	
}