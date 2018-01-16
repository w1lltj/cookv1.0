package com.thread.caveofprog.sec1ep10.reentrantlocks;

public class ReentrantLocksExample2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		Processor2 processor2 = new Processor2();
		
		Thread firstThread =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				processor2.firstThread();
			}
		});
		
		
		Thread secondThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor2.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		firstThread.start();
		secondThread.start();
		
		firstThread.join();
		secondThread.join();
		
		
		processor2.finish();
	}

}
