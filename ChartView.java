package com.willmanson.chartview;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ChartView extends WebView {
	private ArrayList<GenericChart> genericCharts;
	private int currentWidth, currentHeight;
	
	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		genericCharts = new ArrayList<GenericChart>();
	}
	
	public void drawCharts() {
		setInitialScale(200);

		getSettings().setJavaScriptEnabled(true);
		getSettings().setBuiltInZoomControls(true);
		getSettings().setDisplayZoomControls(false);
		loadUrl("file:///android_asset/chartpage.html");
		
		setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url) {
				StringImploder sI = new StringImploder("; ");
				
				StringImploder sITemp = new StringImploder();
				
				for(GenericChart genericChart: genericCharts) {
					sITemp.addNewString("\"" + genericChart.getTargetID() + "\"");
				}
				
				sI.addNewString("insertNewDivs([" + sITemp + "])");
				
				for(GenericChart genericChart: genericCharts) {
					sI.addNewString(genericChart.getDrawVisualizationString(currentWidth, currentHeight));
				}
				
				loadUrl("javascript: " + sI);
			}
		});
	}
	
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
		if(width != 0 && height != 0) {
			currentWidth = width;
			currentHeight = height;
			drawCharts();
		}
	}
	
	public void addGenericChart(String[] columnNames, String chartType, String targetID, int chartTitleType) {
		if(chartType.equals(GenericChart.CHART_TYPE_GEOCHART)) {
			GeoChart newGeoMap = new GeoChart(this, columnNames, targetID, chartTitleType);
			genericCharts.add(newGeoMap);
		}
		else if(chartType.equals(GenericChart.CHART_TYPE_SPARK_LINE)) {
			SparkLine newSparkLine = new SparkLine(this, columnNames, targetID, chartTitleType);
			genericCharts.add(newSparkLine);
		}
		else {
			GenericChart newGenericChart = new GenericChart(this, columnNames, chartType, targetID, chartTitleType);
			genericCharts.add(newGenericChart);
		}
	}
	
	public void addChart(GenericChart genericChart) {
		genericCharts.add(genericChart);
	}
	
	public GenericChart getGenericChart(String targetID) {
		for(GenericChart genericChart: genericCharts) if(genericChart.getTargetID().equals(targetID)) return genericChart;
		return null;
	}
	
	public void addDatum(String targetID, Date rowTitle, String columnName, float datum) {
		GenericChart genericChart = getGenericChart(targetID);
		if(genericChart != null) genericChart.addDatum(rowTitle, columnName, datum);
	}

	public void addDatum(String targetID, int rowTitle, String columnName, float datum) {
		GenericChart genericChart = getGenericChart(targetID);
		if(genericChart != null) genericChart.addDatum(rowTitle, columnName, datum);
	}

	public void addDatum(String targetID, String rowTitle, String columnName, float datum) {
		GenericChart genericChart = getGenericChart(targetID);
		if(genericChart != null) genericChart.addDatum(rowTitle, columnName, datum);
	}
	
	public void addDatum(String targetID, String columnName, float datum) {
		GenericChart genericChart = getGenericChart(targetID);
		if(genericChart != null) genericChart.addDatum(columnName, datum);
	}
	
	public ChartOptions getOptions(String targetID) {
		GenericChart genericChart = getGenericChart(targetID);
		if(genericChart != null) return genericChart.getOptions();
		return null;
	}
}
