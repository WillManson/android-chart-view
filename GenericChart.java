package com.willmanson.chartview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenericChart {
	public final static int TITLE_TYPE_DATE = 0;
	public final static int TITLE_TYPE_STRING = 1;
	public final static int TITLE_TYPE_INT = 2;
	public final static int TITLE_TYPE_EMPTY = 3;
	public final static String CHART_TYPE_AREA = "areachart";
	public final static String CHART_TYPE_SCATTER = "scatterchart";
	public final static String CHART_TYPE_VERTICAL_BAR = "columnchart";
	public final static String CHART_TYPE_HORIZONTAL_BAR = "barchart";
	public final static String CHART_TYPE_LINE = "linechart";
	public final static String CHART_TYPE_PIE = "piechart";
	public final static String CHART_TYPE_INTENSITY = "intensitymap";
	public final static String CHART_TYPE_GEOCHART = "geochart";
	public final static String CHART_TYPE_SPARK_LINE = "sparkline";
	protected ArrayList<Row> rows;
	protected ArrayList<String> columnNames;
	protected String targetID, chartType;
	protected int emptyCounter;
	protected final int titleType;
	protected ChartView chartView;
	protected ChartOptions options;
	protected boolean includeZeros;
	
	public GenericChart(ChartView chartView, String[] columnNames, String chartType, String targetID, int titleType) {
		rows = new ArrayList<Row>();
		this.columnNames = new ArrayList<String>();
		
		setTargetID(targetID);
		setChartType(chartType);
		
		addNewColumnNames(columnNames);
		this.titleType = titleType;
		
		this.chartView = chartView;
		
		options = new ChartOptions();
		options.setWidth(ChartOptions.FULL_PAGE_WIDTH);
		
		emptyCounter = 0;
		
		if(chartType.equals(CHART_TYPE_SCATTER)) {
			setIncludeZeros(false);
		}
		else {
			setIncludeZeros(true);
		}
	}
	
	public void addNewColumnNames(String[] newColumnNames) {
		// Add new column names
		for(String newColumnName: newColumnNames) columnNames.add(newColumnName);
	}
	
	public void addNewColumnNames(Collection<String> newColumnNames) {
		columnNames.addAll(newColumnNames);
	}
	
	public void addNewColumnName(String newColumnName) {
		columnNames.add(newColumnName);
	}
	
	private boolean addDatum(String rowTitle, String columnName, float datum, long comparableNumber) {
		if(!chartType.equals(CHART_TYPE_SCATTER)) {
			for(Row row: rows) {
				if(row.toString().equals(rowTitle)) {
					row.addDatum(columnName, datum);
					return true;
				}
			}
			
			addRow(rowTitle, comparableNumber);
			return addDatum(rowTitle, columnName, datum, comparableNumber);
		}
		else {
			Row row = addRow(rowTitle, comparableNumber);
			row.addDatum(columnName, datum);
			return true;
		}
	}
	
	public boolean addDatum(String rowTitle, String columnName, float datum) {
		if(titleType == TITLE_TYPE_STRING) {
			return addDatum("\"" + rowTitle.replace("'", "") + "\"", columnName, datum, 1L);
		}
		return false;
	}
	
	public boolean addDatum(Date rowTitle, String columnName, float datum) {
		if(titleType == TITLE_TYPE_DATE) {
			String dateString = ChartFunctions.convertDateToString(rowTitle);
			return addDatum(dateString, columnName, datum, rowTitle.getTime());
		}
		return false;
	}
	
	public boolean addDatum(int rowTitle, String columnName, float datum) {
		if(titleType == TITLE_TYPE_INT) {
			return addDatum("" + rowTitle, columnName, datum, (long) rowTitle);
		}
		return false;
	}
	
	public boolean addDatum(String columnName, float datum) {
		if(titleType == TITLE_TYPE_EMPTY) {
			emptyCounter++;
			return addDatum("\"" + emptyCounter + "\"", columnName, datum, 1L);
		}
		return false;
	}
	
	private Row addRow(String rowTitle, long comparableNumber) {
		Row newRow = new Row(rowTitle, columnNames, comparableNumber);
		rows.add(newRow);
		return newRow;
	}
	
	public Row getRow(String rowTitle) {
		for(Row row: rows) {
			if(row.toString().equals(rowTitle)) return row;
		}
		
		return null;
	}
	
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	protected String getChartRows() {
		int toRoundTo = options.getToRoundTo();
		Collections.sort(rows);
		
		StringImploder sI = new StringImploder();
		for(int i = 0; i < rows.size(); i++) {
			sI.addNewString(rows.get(i).getRowString(titleType, toRoundTo, true, includeZeros));
		}
		
		String returnString = "[" + sI + "]";
		
		return returnString;
	}
	
	protected String getChartColumns() {
		StringImploder sI = new StringImploder();
		
		if(titleType == TITLE_TYPE_STRING) sI.addNewString("'Rowtitle':'string'");
		else if(titleType == TITLE_TYPE_DATE) sI.addNewString("'Rowtitle':'date'");
		else if(titleType == TITLE_TYPE_INT) sI.addNewString("'Rowtitle':'number'");
		else if(titleType == TITLE_TYPE_EMPTY) sI.addNewString("'Rowtitle':'string'");
		
		for(int i = 0; i < columnNames.size(); i++) {
			sI.addNewString("'" + columnNames.get(i).replace("'", "") + "':'number'");
		}
		
		String returnString = "{" + sI + "}";
		
		return returnString;
	}
	
	protected String getDrawVisualizationString(int actualWebViewWidth, int actualWebViewHeight) {	
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		options.setDimensions(actualWebViewWidth, actualWebViewHeight);
		String optionsJson = gson.toJson(options);
		
		StringImploder sI = new StringImploder();
		sI.addNewString("'" + getChartType() + "'");
		sI.addNewString("'" + getTargetID() + "'");
		sI.addNewString(getChartColumns());
		sI.addNewString(getChartRows());
		sI.addNewString(optionsJson);
		
		String returnString = "drawVisualization(" + sI + ")";
		
		return returnString;
	}
	
	public String getTargetID() {
		return targetID;
	}
	
	public String getChartType() {
		return chartType;
	}
	
	public void setOptions(ChartOptions options) {
		this.options = options;
	}
	
	public ChartOptions getOptions() {
		return options;
	}
	
	public void setIncludeZeros(boolean includeZeros) {
		this.includeZeros = includeZeros;
	}
	
	public boolean getIncludeZeros() {
		return includeZeros;
	}
}
