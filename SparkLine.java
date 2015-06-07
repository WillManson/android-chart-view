package com.willmanson.chartview;

import java.util.Collections;

import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SparkLine extends GenericChart {
	public SparkLine(ChartView chartView, String[] columnNames, String targetID, int titleType) {
		super(chartView, columnNames, GenericChart.CHART_TYPE_SPARK_LINE, targetID, titleType);
	}
	
	public String getChartColumns() {
		StringImploder sI = new StringImploder();
			
		sI.addNewString("'Empty'");
		
		for(int i = 0; i < columnNames.size(); i++) {
			sI.addNewString("'" + columnNames.get(i) + "'");
		}
		
		String returnString = "[[" + sI + "]]";
		
		return returnString;
	}
}
