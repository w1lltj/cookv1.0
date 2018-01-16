package com.thread.semaphore;

import java.util.concurrent.Semaphore;

public class Connection2 {
	
	private static Connection2 instance2 = new Connection2();
	
	private int connectionCount = 0;
	
	private Semaphore sem = new Semaphore(10,true);
	
	public static Connection2 getInstance2() {
		return instance2;
	}
	
	public void executeConnect() {
		
		try {
			
			sem.acquire();
			connect();
			
		} catch(InterruptedException err) {
			err.printStackTrace();
		} finally {
			sem.release();
		}
		
	}
	
	
	public void connect() {
		
		synchronized(this) {
			connectionCount++;
			System.out.println("current connection count: " + connectionCount + 
					" ; ThreadName: " + Thread.currentThread().getName());
		}
		
		try {
			Thread.sleep(2000);
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		synchronized(this) {
			connectionCount--;
			System.out.println("decrement current connection count to: " + connectionCount  + 
					" ; ThreadName: " + Thread.currentThread().getName());
		}
		
	}

}
