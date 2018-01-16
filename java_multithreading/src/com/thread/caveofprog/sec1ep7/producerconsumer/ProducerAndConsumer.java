package com.thread.caveofprog.sec1ep7.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumer {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	private static Random random = new Random();

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Application Completed ...");

	}

	private static void producer() throws InterruptedException {

		while (true) {
			queue.put(random.nextInt(100));
		}

	}

	private static void consumer() throws InterruptedException {

		while (true) {
			Thread.sleep(1000);

			if (queue.size()>1) {
				Integer val = queue.take();

				System.out.println("Value Taken is: " + val + " ; Queue Size: " + queue.size());
			}
		}
	}
}
