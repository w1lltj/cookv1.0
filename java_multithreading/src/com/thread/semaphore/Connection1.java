package com.thread.semaphore;

public class Connection1 {
	
	private static Connection1 instance1 = new Connection1();
	
	private int connectionCount = 0;
	
	public static Connection1 getInstance1() {
		return instance1;
	}
	
	public void connect() {
		
		synchronized(this) {
			connectionCount++;
			System.out.println("current connection count: " + connectionCount + " ; ThreadName: " + 
			Thread.currentThread().getName());
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
