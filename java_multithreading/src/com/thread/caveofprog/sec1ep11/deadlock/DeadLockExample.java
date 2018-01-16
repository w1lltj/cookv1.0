package com.thread.caveofprog.sec1ep11.deadlock;

public class DeadLockExample {

	public static void main(String[] args) throws InterruptedException {

		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				processor.firstThread();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				processor.secondThread();
			}
		});

		long startTime = System.currentTimeMillis();
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();

		long endTime = System.currentTimeMillis();

		
		processor.complete();
		System.out.println("Elapsed time: " + (endTime-startTime));

	}

}
