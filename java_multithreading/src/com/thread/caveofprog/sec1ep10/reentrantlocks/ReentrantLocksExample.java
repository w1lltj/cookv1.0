package com.thread.caveofprog.sec1ep10.reentrantlocks;

public class ReentrantLocksExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		Processor processor = new Processor();
		
		Thread firstThread =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				processor.firstThread();
			}
		});
		
		
		Thread secondThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				processor.secondThread();
			}
		});
		
		
		
		firstThread.start();
		secondThread.start();
		
		firstThread.join();
		secondThread.join();
		
		
		processor.finish();
	}

}
