package com.willmanson.chartview;

import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeoChart extends GenericChart {
	public GeoChart(ChartView chartView, String[] columnNames, String targetID, int titleType) {
		super(chartView, columnNames, GenericChart.CHART_TYPE_GEOCHART, targetID, titleType);
	}
	
	public String getChartColumns() {
		StringImploder sI = new StringImploder();
		
		sI.addNewString("'Country'");
		sI.addNewString("'" + columnNames.get(0) + "'");
		
		String returnString = "[[" + sI + "]]";
		
		return returnString;
	}
}
