package com.thread.caveofprog.sec1ep12.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoresExample1 {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();

		long startTime = System.currentTimeMillis();

		for (int i = 0; i <200; i++) {

			executor.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					/*run instance1 by 200 different Threads*/
					Connection1.getInstance1().connect();
					
					/*compare to the following: running 200 different instances (copies of instance1) run by
					 *  200 different Threads*/
					//new Connection1().connect();
				}

			});

		}

		executor.shutdown();
		executor.awaitTermination(10000, TimeUnit.MILLISECONDS);

		long endTime = System.currentTimeMillis();

		System.out.println("Threads completed!");
		System.out.println("Time elapsed: " + (endTime - startTime));

	}

}
