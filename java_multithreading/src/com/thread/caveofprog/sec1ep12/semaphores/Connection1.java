package com.thread.caveofprog.sec1ep12.semaphores;

public class Connection1 {

	
	private static Connection1 instance1 = new Connection1();

	private int connectionCount = 0;

	/*
	 * static modifier is used making the access to getInstance possible without
	 * having to instantiate the object Connection1
	 */
	public static Connection1 getInstance1() {
		return instance1;
	}

	public void connect() {

		synchronized (this) {
			connectionCount++;
			System.out.println("current number of connections: " + connectionCount);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			connectionCount--;
		}

	}
	
}
