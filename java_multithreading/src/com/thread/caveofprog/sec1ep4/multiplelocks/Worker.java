package com.thread.caveofprog.sec1ep4.multiplelocks;

import java.util.*;

public class Worker {

	private static List<Integer> list1 = new ArrayList<>();
	private static List<Integer> list2 = new ArrayList<>();

	static Random random = new Random();

	public static void stageOne() {

		try {
			Thread.sleep(1);
		} catch (InterruptedException err) {
			err.printStackTrace();
		}

		list1.add(random.nextInt(100));

	}

	public static void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException err) {
			err.printStackTrace();
		}

		list2.add(random.nextInt(100));

	}

	public static void process() {
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	
	public static void main(String[] args) {
		System.out.println("Hello");

		long startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		
		process();
		
		long endTime = System.currentTimeMillis();
		System.out.println("End Time: " + endTime);
		
		System.out.println("Time taken: " + (endTime-startTime));
		System.out.println("list1 size: " + list1.size());
		System.out.println("list2 size: " + list2.size());
		
	}

}
