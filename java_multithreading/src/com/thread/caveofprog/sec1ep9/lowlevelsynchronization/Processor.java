package com.thread.caveofprog.sec1ep9.lowlevelsynchronization;

import java.util.LinkedList;
import java.util.Random;

class Processor {

	private LinkedList<Integer> list = new LinkedList<>();
	private final int MAX_SIZE = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		int val = 0;

		while (true) {

			synchronized (lock) {
				
				while (list.size() >= MAX_SIZE) {
					lock.wait();

				}
				
				list.add(val++);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		
		Random random = new Random();
		
		while(true) {
			
			synchronized(lock) {
				
				while(list.size()<=0) {
					lock.wait();
				}
				
				System.out.print("(Bfr Remove) List size: " + list.size());
				int val_taken = list.removeFirst();
				System.out.println(" ; Val taken: " + val_taken + "(Aft Remove) List size: " + list.size());
				lock.notify();
				
				Thread.sleep(random.nextInt(1000));
			}
			//Thread.sleep(random.nextInt(1000));
		}

	}

}
