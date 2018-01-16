package com.thread.caveofprog.sec1ep12.semaphores;

import java.util.concurrent.Semaphore;

public class Connection2 {

	private static Connection2 instance2 = new Connection2();

	private int connectionCount = 0;

	private Semaphore sem = new Semaphore(10, true);

	public static Connection2 getInstance2() {
		return instance2;
	}

	public void processConnect() throws InterruptedException {

		synchronized (this) {
			connectionCount++;
			System.out.println("Current number of connections: " + connectionCount);
		}

		Thread.sleep(2000);

		synchronized (this) {
			connectionCount--;
		}

	}

	public void connect() {

		try {
			sem.acquire();
			processConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}

	}

}
