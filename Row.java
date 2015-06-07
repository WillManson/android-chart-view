package com.willmanson.chartview;

import java.util.ArrayList;
import android.util.Log;

public class Row implements Comparable {
	protected ArrayList<String> columnNames;
	protected ArrayList<Float> data;
	protected String printRowName;
	protected long comparableNumber;
	
	protected Row(String rowName, ArrayList<String> columnNames, long comparableNumber) {
		this.columnNames = columnNames;
		this.printRowName = rowName;
		data = new ArrayList<Float>();
		this.comparableNumber = comparableNumber;
		setDefaultData();
	}
	
	public String toString() {
		return printRowName;
	}
	
	protected boolean addDatum(String columnName, float datum) {
		int count = 0;
		
		for(String compareColumnName: columnNames) {
			if(compareColumnName.equals(columnName)) {
				data.set(count, Float.valueOf(datum));
				return true;
			}
			
			count++;
		}
		
		return false;
	}
	
	protected String getRowString(int titleType, int toRoundTo, boolean includeRowTitle, boolean includeZeros) {
		StringImploder sI = new StringImploder();
		
		if(includeRowTitle) {
			sI.addNewString(printRowName);
		}
		
		for(int i = 0; i < columnNames.size(); i++) {
			if(data.get(i).floatValue() != 0.0f || includeZeros) {
				if(toRoundTo >= 0) {
					sI.addNewString(String.format("%." + toRoundTo + "f", data.get(i).floatValue()));
				}
				else {
					sI.addNewString("" + data.get(i).floatValue());
				}
			}
			else sI.addNewString("null");
		}
	
		String returnString = "[" + sI + "]";
		
		return returnString;
	}
	
	protected void setDefaultData() {
		for(int i = 0; i < columnNames.size(); i++) {
			data.add(Float.valueOf(0));
		}
	}
	
	public int compareTo(Object object) {
		Row compareRow = (Row) object;
		
		if(comparableNumber > compareRow.comparableNumber) return 1;
		else if(comparableNumber == compareRow.comparableNumber) return 0;
		else return -1;
	}
}
