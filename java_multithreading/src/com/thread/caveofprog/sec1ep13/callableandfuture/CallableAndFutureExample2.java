package com.thread.caveofprog.sec1ep13.callableandfuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableAndFutureExample2 {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		Future<?> future = executor.submit(new Callable<Void>() { //if you do not expect any return value (return value is null)

			@Override
			public Void call() throws Exception {
				// TODO Auto-generated method stub

				System.out.println("Start thread ...");

				Random random = new Random();
				int duration = random.nextInt(5000);
				
				if(duration>2000) {
					throw new IOException("Thread sleeps too long!");
				}
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("End thread ...");

				return null;
			}
		});

		executor.shutdown();

		
		/*future.get() will be blocked (only executed) until all the
		threads are completed*/
		//executor.awaitTermination(10000, TimeUnit.MILLISECONDS);

				
		try {
			System.out.println("Thread sleep duration: " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			/*if thread sleeps for more than 2secs, throws IOException.
			 * ExecutionException catches the IOException.
			 * Cach ExecutionException into IOException and 
			 * print out the error message*/
			IOException err = (IOException)e.getCause();
			System.out.println(err.getMessage());
			
		}


	}

}
