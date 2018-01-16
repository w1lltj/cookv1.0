package com.thread.lowlevelsync;

public class Main {

	public static void main (String[] args) {
		
		MainProcessor mainProcessor = new MainProcessor();
		
		
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mainProcessor.producer();
				} catch (InterruptedException err) {
					
				}
			}
			
		});
		
		
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mainProcessor.consumer();
				} catch (InterruptedException err) {
					// TODO Auto-generated catch block
					
				}
			}
		});
		
		
		producer.start();
		consumer.start();
		
	}
	
}
