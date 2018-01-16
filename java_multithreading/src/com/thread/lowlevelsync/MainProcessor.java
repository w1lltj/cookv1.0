package com.thread.lowlevelsync;

import java.util.LinkedList;

public class MainProcessor {
	
	
	private static final int LIMIT = 10;
	private LinkedList<Integer> numList = new LinkedList<Integer>();
	Object lock = new Object();
	
	public void producer() throws InterruptedException {
		
		int val = 0;
		
		while(true) {
			
			synchronized(lock) {
				
				while(numList.size()==LIMIT) {
					lock.wait();
				}
				
				numList.add(val);
				val++;
				lock.notify();
				
				System.out.println("producer inner-block");
			}

			System.out.println("producer outer-block");
			
		}
		
	}

	
	public void consumer() throws InterruptedException {
		
		while(true) {
			
			synchronized(lock) {
				
				while(numList.size()<=0) {
					lock.wait();
				}
				
				int value = numList.removeFirst(); //value in consumer <> val in producer
				System.out.print("Size of numList: " + numList.size());
				System.out.println(" & value of val: " + value);
				
				lock.notify();
				
				System.out.println("consumer inner-block");
				Thread.sleep(1000);
			}

			System.out.println("consumer outer-block"); //outside synchronized block statement is not guaranteed to be executed together with statements inside synchronized block
			//Thread.sleep(1000);
			
		}
		
	}
}
