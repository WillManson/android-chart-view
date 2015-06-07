package com.willmanson.chartview;

import java.util.ArrayList;

public class StringImploder {
	private ArrayList<String> strings;
	private String delimiter;
	
	protected StringImploder(String delimiter) {
		strings = new ArrayList<String>();
		this.delimiter = delimiter;
	}
	
	protected StringImploder() {
		this(", ");
	}
	
	protected void addNewString(String newString) {
		strings.add(newString);
	}
	
	public String toString() {
		String returnString = "";
		
		for(int i = 0; i < strings.size(); i++) {
			if(i != 0) returnString += delimiter;
			returnString += strings.get(i);
		}
		
		return returnString;
	}
}
