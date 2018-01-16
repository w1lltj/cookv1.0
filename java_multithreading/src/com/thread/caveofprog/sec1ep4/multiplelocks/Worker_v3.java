package com.thread.caveofprog.sec1ep4.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker_v3 {

	private static List<Integer> list1 = new ArrayList<>();
	private static List<Integer> list2 = new ArrayList<>();

	static Random random = new Random();

	static Object lock1 = new Object();
	static Object lock2 = new Object();

	public static void stageOne() {

		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException err) {
				err.printStackTrace();
			}

			list1.add(random.nextInt(100));
		}

	}

	public static synchronized void stageTwo() {

		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException err) {
				err.printStackTrace();
			}

			list2.add(random.nextInt(100));

		}

	}

	public static void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public static void main(String[] args) {
		System.out.println("Start Program...");

		long startTime = System.currentTimeMillis();
		// System.out.println("Start Time: " + startTime);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException err) {
			err.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		// System.out.println("End Time: " + endTime);

		System.out.println("Time taken: " + (endTime - startTime));
		System.out.println("list1 size: " + list1.size());
		System.out.println("list2 size: " + list2.size());

	}
}
