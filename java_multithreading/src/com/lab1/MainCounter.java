package com.lab1;

public class MainCounter {
	
	public static void main(String[] args) {
		
		Customer customer1 = new Customer("Will", 8923412.99);
		System.out.println("Before Change\n=====================");
		System.out.println("customer1\n" + customer1.getName() + "\n" + customer1.getBalance() +"\n");
		
		//Customer customer2;
		Customer customer2 = new Customer("Frank", 80989.99);
		System.out.println("customer2\n" + customer2.getName() + "\n" + customer2.getBalance()+"\n");
		
		customer2 = customer1;
		customer2.setBalance(15885456.99);
		System.out.println("After Change\n=====================");
		System.out.println("customer1\n" + customer1.getName() + "\n" + customer1.getBalance()+"\n");
		System.out.println("customer2\n" + customer2.getName() + "\n" + customer2.getBalance()+"\n");
		
		//class inside public static void main: modifier cannot be public.
		//typing 'public class TestContruct' will cause an error.
		class TestConstruct {
			
			public TestConstruct() {
				System.out.println("this is constructor testing!");
			}
			
		}
		
		TestConstruct testConstruct = new TestConstruct();
		
	}
	
	
}
