package com.thread;

public class ThreadTest {
	
	public static void main(String[] args) {
	
		/*using Thread inheritance*/
		ThreadSample1 threadSample1 = new ThreadSample1();
		threadSample1.start();
		
		
		for(int i=0; i<100; i++) {
			System.out.println("<main>This is Thread Practice!" + ", value of i: " + i);
		}

		/*using Thread inheritance*/
		ThreadSample2 threadSample2 = new ThreadSample2();
		threadSample2.start();
		
		/*using Runnable interface*/
		Thread runnableSample1 = new Thread(new RunnableSample());
		runnableSample1.start();
		
		/*using Runnable interface*/
		Thread runnableSample2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<100; i++) {
					System.out.println("<RunnableSample2>Current ThreadID: " + Thread.currentThread().getId() + ", value of i: " + i);
				}
			}
		});
		runnableSample2.start();
		
	}
	
	static class ThreadSample2 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//super.run();
			
			for(int i=0; i<100; i++) {
				System.out.println("<ThreadSample2>Current ThreadID: " + Thread.currentThread().getId() + ", value of i: " + i);
			}
			
		}
	}

}

class ThreadSample1 extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		
		for(int i=0; i<100; i++) {
			System.out.println("<ThreadSample1>Current ThreadID: " + Thread.currentThread().getId() + ", value of i: " + i);
		}
		
	}
	
}

class RunnableSample implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0; i<100; i++) {
			System.out.println("<RunnableSample1>Current ThreadID: " + Thread.currentThread().getId() + ", value of i: " + i);
		}
		
		try {
			Thread.sleep(2000);	//pause this thread for 2secs	
		} catch(InterruptedException err) {
			err.getStackTrace();
		}

	}
}

/* Summary about Thread
 * 
Thread method - inherit Thread class or implements Runnable interface:
	- Thread.join - wait until the thread finish executing and then move on to the next one
	- Thread.join(2000) - wait the thread to execute for 2secs and then move on to next one
	- Thread.sleep(2000) - make the thread to sleep for 2secs

Thread is randomly run and competing with each other to get executed:
	- synchronized - if a method is shared among multiple threads, 
		it is to wait until one thread finishes executing a method and then proceed with other thread(s)
	- (Thread interference) reentrant lock - using method (and able to specify which line to) lock and unlock without 
		having to wait until certain method finish being executed (unlike synchronized)
	- (Thread interference) trylock method
	- (Thread interference) wait & notify - another method of thread interference to move from one thread to 
		another thread execution
	
*/	




