package com.thread.caveofprog.sec1ep14.interruptingthread;

import java.util.Random;

public class InterruptingThreadExample {

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();

		System.out.println("Start program ...");

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Start thread ...");

				for (int i = 0; i < 1E6; i++) {

					try {
						Thread.sleep(1000);
						//Math.sin(random.nextDouble());
						System.out.println("test :" + i);
					} catch (InterruptedException err) {
						System.out.println("Thread interrupted!");
						break;
					}

				}
			}
		});

		t1.start();

		Thread.sleep(5000);

		t1.interrupt();

		t1.join();

		System.out.println("Finish program ...");

	}

}
