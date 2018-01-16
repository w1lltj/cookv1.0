package com.thread;

public class ThreadJoin {
	
	private static int countNum = 0;
	
	/*synchronized ... makes the method only accessible once at a time. e.g. when it is accessed by t1, 
	t2 have to wait until t1 finish*/
	public static synchronized void countNum() {
		countNum++;
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<10000; i++) {
					//countNum++;
					countNum();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<10000; i++) {
					//countNum++;
					countNum();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); //wait until t1 is completed then continue the next line
			//t1.join(1000); //wait t1 to be executed for 1sec then continue the next line
			t2.join(); //wait until t2 is completed then continue the next line
		} catch(InterruptedException err) {
			err.printStackTrace();
		}
		
		
		System.out.println("value of countNum: " + countNum);
		
	}
	
}
