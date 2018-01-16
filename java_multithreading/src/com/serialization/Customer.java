package com.serialization;

import java.io.Serializable;

public class Customer implements Serializable{
	
    /*this is necessary to avoid java.io.InvalidClassException
     * it could be any random number to identify the serialized class to avoid
     * JVM automatically randomly generate any unique ID (JVM will automatically generate
     * if the ID is not fixed by us*/
    
	private static final long serialVersionUID = 1L; //static final is to avoid it being override
	private String firstName;
	private String lastName;
	private int age;
	
	public Customer(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "FirstName: " + firstName + " LastName: " + lastName + " Age: " + age + "\n";
	}
}
