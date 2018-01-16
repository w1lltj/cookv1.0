package com.serialization;


import java.util.*;

public class SerializationApp {

	public static void main(String[] args) {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		customers.add(new Customer("Amelia","Tanur",25));
		customers.add(new Customer("Harry","Stones",26));
		customers.add(new Customer("Haddy","Salim",27));
		customers.add(new Customer("Will","Tjia",28));
		customers.add(new Customer("Kim","Tong",29));
		
		System.out.println(customers);
		
		Serialize serializeCustomers = new Serialize();
		
		/*Serialization*/
		serializeCustomers.SerializeFactory(customers, "src/com/serialization/Customers.ser");
		System.out.println("Serialization done!\n");
		
		/*Deserialization*/
		List<Customer> D_customers = 
				(ArrayList<Customer>)serializeCustomers.DeserializeFactory("src/com/serialization/Customers.ser");
		System.out.println(D_customers);
		System.out.println("Deserialization done!");
	}
}
