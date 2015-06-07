package com.willmanson.chartview;

import java.util.Date;

public class ChartFunctions {
	public static String convertDateToString(Date date) {
		StringImploder sI = new StringImploder();
		sI.addNewString("" + date.getYear());
		sI.addNewString("" + date.getMonth());
		sI.addNewString("" + date.getDate());

		String returnString = "new Date(" + sI + ")";
		
		return returnString;
	}
}
