package com.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main1 {

	public static void main(String[] args) throws Exception {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0; i<10; i++) {
			executor.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Connection1.getInstance1().connect();
					
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(20, TimeUnit.SECONDS);
		
	}
	
}
