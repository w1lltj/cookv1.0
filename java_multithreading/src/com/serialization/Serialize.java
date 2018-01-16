package com.serialization;

import java.util.*;
import java.io.*;

public class Serialize {

	public void SerializeFactory(List<Customer> customers, String fileName) {
		
		//try with resources - objOutStream will be automatically closed at the end of the command
		try(ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
			objOutStream.writeObject(customers);
		} catch(IOException err) {
			System.out.println(err.getMessage());
			err.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> DeserializeFactory(String fileName) {
		
		ArrayList<Customer> deserializedCustomers = null;
		
		try(ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(fileName))) {
			deserializedCustomers = (ArrayList<Customer>)objInStream.readObject();
		} catch(IOException | ClassNotFoundException err) {
			System.out.printf("Problem occurred deserializing file: %s %n", fileName);
			System.out.println(err.getMessage());
		}
		
		return deserializedCustomers;
	}
	
}
