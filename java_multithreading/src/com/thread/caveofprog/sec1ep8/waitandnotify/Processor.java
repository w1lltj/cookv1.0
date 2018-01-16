package com.thread.caveofprog.sec1ep8.waitandnotify;

import java.util.Scanner;

public class Processor {

	public void producer() throws InterruptedException {

		synchronized (this) {
			System.out.println("Producer Method: producing code ...");
			wait();
			System.out.println("Producer Method: test program resumes ...");
		}

	}

	public void consumer() throws InterruptedException {
		Scanner input = new Scanner(System.in);
		Thread.sleep(2000);

		synchronized (this) {
			System.out.print("Consumer Method: press Enter to continue ...");
			input.nextLine();
			System.out.println("Consumer Method: Enter is pressed ...");
			notify();
			Thread.sleep(5000);
			
		}

	}

}
