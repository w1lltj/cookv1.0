package com.thread.caveofprog.sec1ep10.reentrantlocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor2 {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private void increment() {

		for (int i = 0; i < 1000; i++) {
			count++;
		}

	}

	public void firstThread() {

		lock.lock();

		System.out.println("[firstThread] Start running program ...");
		try {
			condition.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[firstThread] Re-acquired the lock ...");
		
		/*
		 * surround the increment() method with try-finally to make sure unlock is
		 * executed even if there is any error on the method execution
		 */
		try {
			increment();
		} finally {
			lock.unlock();
		}

	}

	@SuppressWarnings("resource")
	public void secondThread() throws InterruptedException {
		
		Thread.sleep(1000);
		lock.lock();

		System.out.println("[secondThread] acquired the lock ...");
		System.out.println("[secondThread] Enter return key ...");
		
		new Scanner(System.in).nextLine();
		
		System.out.println("[secondThread] Return key is pressed ...");
		condition.signal();
		
		/*
		 * surround the increment() method with try-finally to make sure unlock is
		 * executed even if there is any error on the method execution
		 */
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}

	public void finish() {
		System.out.println("The value of count: " + count);
	}
}
