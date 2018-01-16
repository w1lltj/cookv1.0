package com.thread.caveofprog.sec1ep12.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoresExample2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<200; i++) {
			executor.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Connection2.getInstance2().connect();
				}
			});
		}
		
		executor.shutdown();
		
		executor.awaitTermination(20000, TimeUnit.MILLISECONDS);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Threads completed!");
		System.out.println("Time elapsed: " + (endTime-startTime));
	}

}
