package com.lab2;
import java.util.Scanner;


public class MainFrame {
	
	private static Scanner scanner = new Scanner(System.in);
	private static Button1 btn1 = new Button1("BUTTON No.1");
	
	public static void main(String[] args) {
		
		class ClickListener implements OnClickListener{
			
			public ClickListener() {
				System.out.println("Initialized ");
			}
			
			@Override
			public void onClick(String btnValue) {
				System.out.println(btnValue + " is clicked!");
				
			}
			
		}
		
		btn1.setOnClickListener(new ClickListener());
		listen();
		
		/*Testing - instances called in this main static method shall be static if no causes error
		Test2 test2 = new Test2();
		test2.test2_print();
		
		Test test = new Test();*/

	}
	
	
	public static void listen() {
		boolean quit = false;
		
		while(!quit) {
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
				case 0:
					System.out.println("Exiting");
					quit = true;
					break;
				case 1:
					/*both following codes serve similar function*/
					btn1.getOnClickListener().onClick(btn1.getBtnValue());
					btn1.onClick();
					break;
				
			}
		}
	}
	
	public class Test {
		
		public void testing() {
			System.out.println("Testing");
		}
	}
	
	public class Test2 {
		private int seat = 'A' + 10;
		
		public void test2_print() {
			System.out.println("Seat is " + seat);
		}
		
	}
	
}
