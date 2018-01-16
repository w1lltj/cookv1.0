package com.thread.caveofprog.sec1ep6.countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	
	CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Start Program ...");
		
		try {
			Thread.sleep(1000);
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		latch.countDown();
	}
}


public class CountDownLatchExample {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(5);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<5; i++) {
			executor.submit(new Processor(latch));
		}
		
		
		try {
			latch.await();
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		
		executor.shutdown();
		System.out.println("Tasks Completed ...");
	}
	
}
