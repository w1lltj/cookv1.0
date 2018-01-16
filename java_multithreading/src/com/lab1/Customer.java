package com.lab1;

public class Customer { //if the class is final, it can't be extended
	
	private String name;
	private double balance;
	
	public Customer() { //if the constructor is private, the class can't be instantiated
		super();
	}
	
	public Customer(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) { //if method is final, it can't be overridden
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
