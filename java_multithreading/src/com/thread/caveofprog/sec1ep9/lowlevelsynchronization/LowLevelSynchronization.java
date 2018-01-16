package com.thread.caveofprog.sec1ep9.lowlevelsynchronization;

public class LowLevelSynchronization {
	
	public static void main(String[] args) throws InterruptedException {
		
		Processor processor = new Processor();
		
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		producer.start();
		consumer.start();
		
		
		producer.join();
		consumer.join();
	}

}
