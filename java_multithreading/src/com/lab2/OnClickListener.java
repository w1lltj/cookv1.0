package com.lab2;

public interface OnClickListener {
	
	/*Interface can have variables only if they are public static type that won't change 
	Non-static type requires instance*/
	//public static final int abc = 1; OK
	//public static int abc = 1; OK
	//public int abc;  NG
	
	void onClick(String btnValue);
}
