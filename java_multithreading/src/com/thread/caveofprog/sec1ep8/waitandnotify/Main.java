package com.thread.caveofprog.sec1ep8.waitandnotify;

import java.util.Scanner;

public class Main {

	public static void main (String[] args) throws InterruptedException {
		
		Processor processor = new Processor();
		
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.producer();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.consumer();
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
