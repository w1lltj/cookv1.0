package com.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main2 {

	public static void main (String[] args) throws Exception {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0; i<10; i++) { 
			/*when thread is too many, it is better to use semaphore so you do not overuse 
			your resources because you can allocate how many you want to run*/
			executor.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Connection2.getInstance2().executeConnect();
					
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(20, TimeUnit.SECONDS);
		
	}
	
}
