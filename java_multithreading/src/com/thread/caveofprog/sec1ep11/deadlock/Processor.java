package com.thread.caveofprog.sec1ep11.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Random random = new Random();
	
	private Lock lock =  new ReentrantLock();
	
	
	public void firstThread() {
		
		for(int i=0; i<10000; i++) {
			
			lock.lock();
			
			try {
				/*using Object Account directly to manipulate both acc1 & acc2 value*/
				Account.transfer(acc1, acc2, random.nextInt(100));
				//acc1.transfer(acc1, acc2, random.nextInt(100));				
			} finally {
				lock.unlock();
			}

			
		}
	}
	
	
	public void secondThread() {
		
		for(int i=0; i<10000; i++) {
			
			lock.lock();
			
			try {
				/*using Object Account directly to manipulate both acc1 & acc2 value*/
				Account.transfer(acc2, acc1, random.nextInt(100));
				//acc2.transfer(acc2, acc1, random.nextInt(100));				
			} finally {
				lock.unlock();
			}

			
		}
		
	}
	
	
	public void complete() {
		System.out.println("acc1 balance: " + acc1.getBalance());
		System.out.println("acc2 balance: " + acc2.getBalance());
		System.out.println("total acc1 & acc2 balance: " + (acc1.getBalance() + acc2.getBalance()));
	}

}
