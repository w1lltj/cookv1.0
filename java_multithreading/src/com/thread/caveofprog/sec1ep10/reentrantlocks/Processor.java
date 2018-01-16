package com.thread.caveofprog.sec1ep10.reentrantlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	private int count = 0;
	private Lock lock = new ReentrantLock();

	private void increment() {

		for (int i = 0; i < 1000; i++) {
			count++;
		}

	}

	public void firstThread() {

		lock.lock();

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

	public void secondThread() {
		
		lock.lock();

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
