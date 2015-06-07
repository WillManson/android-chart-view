package com.willmanson.chartview;

import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class GraphActivity extends Activity {
	ChartView graphView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_graph);
		
		graphView = (ChartView) findViewById(R.id.chartview);
ChartOptions options;
		
		Random random = new Random();
		
		// Graph1
		String[] columnNames = new String[]{ "Website One", "Website Two", "Website Three" };
		graphView.addGenericChart(columnNames, GenericChart.CHART_TYPE_AREA, "chart_div", GenericChart.TITLE_TYPE_DATE);
		
		for(int i = 1; i < 30; i++) {
			graphView.addDatum("chart_div", new Date(2012, 2, i), "Website One", (float) random.nextInt(2800) + 2);
			graphView.addDatum("chart_div", new Date(2012, 2, i), "Website Two", (float) random.nextInt(2800) + 2);
			graphView.addDatum("chart_div", new Date(2012, 2, i), "Website Three", (float) random.nextInt(2800) + 2);
		}
		
		options = graphView.getOptions("chart_div");
		options.setTitle("Example 1");
		options.hAxis.setTitle("Date");
		options.vAxis.setTitle("Revenue");
		options.hAxis.titleTextStyle.setColor("#ff0000");
		options.vAxis.setFormat(ChartOptions.FORMAT_POUND_WITH_COMMA);
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);
		
		// Graph10
		String[] columnNames10 = new String[]{ "Popularity" };
		graphView.addGenericChart(columnNames10, GenericChart.CHART_TYPE_GEOCHART, "chart_div10", GenericChart.TITLE_TYPE_STRING);
		
		graphView.addDatum("chart_div10", "Kansas", "Popularity", 400.0f);
		graphView.addDatum("chart_div10", "Texas", "Popularity", 600.0f);
		graphView.addDatum("chart_div10", "West Virginia", "Popularity", 900.0f);
		graphView.addDatum("chart_div10", "Minnesota", "Popularity", 200.0f);
		graphView.addDatum("chart_div10", "Iowa", "Popularity", 350.0f);
		graphView.addDatum("chart_div10", "Alaska", "Popularity", 690.0f);

		graphView.addDatum("chart_div10", "Boston", "Popularity", 350.0f);
		graphView.addDatum("chart_div10", "Mountain View", "Popularity", 1300.0f);
		
		options = graphView.getOptions("chart_div10");
		options.setTitle("Example 10");
		options.setDisplayMode("markers");
		options.setRegion("US");
		options.colorAxis.addColor("#ff0000");
		options.colorAxis.addColor("#0000ff");
		
		// Graph2
		String[] columnNames2 = new String[]{ "Website One", "Website Two", "Website Three" };
		graphView.addGenericChart(columnNames2, GenericChart.CHART_TYPE_SCATTER, "chart_div2", GenericChart.TITLE_TYPE_DATE);
		
		for(int i = 1; i < 8; i++) {
			if(random.nextInt(2) == 0) graphView.addDatum("chart_div2", new Date(2012, 2, i), "Website One", (float) random.nextInt(28) + 2);
			if(random.nextInt(2) == 0) graphView.addDatum("chart_div2", new Date(2012, 2, i), "Website Two", (float) random.nextInt(28) + 2);
			if(random.nextInt(2) == 0) graphView.addDatum("chart_div2", new Date(2012, 2, i), "Website Three", (float) random.nextInt(28) + 2);
		}
		
		options = graphView.getOptions("chart_div2");
		options.setTitle("Example 2");
		options.hAxis.setTitle("Date of sale");
		options.vAxis.setTitle("Price of item sold");
		options.hAxis.titleTextStyle.setColor("#ff0000");
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);
		options.vAxis.setFormat(ChartOptions.FORMAT_POUND_WITH_COMMA);
		
		// Graph3
		String[] columnNames3 = new String[]{ "Website One", "Website Two", "Website Three" };
		graphView.addGenericChart(columnNames3, GenericChart.CHART_TYPE_HORIZONTAL_BAR, "chart_div3", GenericChart.TITLE_TYPE_STRING);
		
		for(int i = 1; i < 7; i++) {
			graphView.addDatum("chart_div3", "" + i, "Website One", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div3", "" + i, "Website Two", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div3", "" + i, "Website Three", (float) random.nextInt(28) + 2);
		}
		
		options = graphView.getOptions("chart_div3");
		options.setTitle("Example 3");
		options.hAxis.setTitle("Sales");
		options.vAxis.setTitle("Products");
		options.hAxis.titleTextStyle.setColor("#ff0000");
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);
		
		// Graph9
		String[] columnNames9 = new String[]{ "Popularity" };
		graphView.addGenericChart(columnNames9, GenericChart.CHART_TYPE_GEOCHART, "chart_div9", GenericChart.TITLE_TYPE_STRING);
		
		graphView.addDatum("chart_div9", "RU", "Popularity", 400.0f);
		graphView.addDatum("chart_div9", "FR", "Popularity", 600.0f);
		graphView.addDatum("chart_div9", "Latvia", "Popularity", 900.0f);
		graphView.addDatum("chart_div9", "China", "Popularity", 200.0f);
		graphView.addDatum("chart_div9", "Japan", "Popularity", 350.0f);
		graphView.addDatum("chart_div9", "Jamaica", "Popularity", 1200.0f);
		graphView.addDatum("chart_div9", "Spain", "Popularity", 350.0f);
		graphView.addDatum("chart_div9", "USA", "Popularity", 1900.0f);
		graphView.addDatum("chart_div9", "South Africa", "Popularity", 350.0f);
		graphView.addDatum("chart_div9", "Brazil", "Popularity", 1000.0f);
		
		options = graphView.getOptions("chart_div9");
		options.setTitle("Example 9");
		
		// Graph4
		String[] columnNames4 = new String[]{ "Website One", "Website Two", "Website Three" };
		graphView.addGenericChart(columnNames4, GenericChart.CHART_TYPE_VERTICAL_BAR, "chart_div4", GenericChart.TITLE_TYPE_STRING);
		
		for(int i = 1; i < 9; i++) {
			graphView.addDatum("chart_div4", "" + i, "Website One", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div4", "" + i, "Website Two", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div4", "" + i, "Website Three", (float) random.nextInt(28) + 2);
		}
		
		options = graphView.getOptions("chart_div4");
		options.setTitle("Example 4");
		options.hAxis.setTitle("Products");
		options.vAxis.setTitle("Performance");
		options.hAxis.titleTextStyle.setColor("#ff0000");;
		options.vAxis.setFormat(ChartOptions.FORMAT_PERCENTAGE_WITH_COMMA);
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);
		
		// Graph5
		String[] columnNames5 = new String[]{ "Website One", "Website Two", "Website Three" };
		graphView.addGenericChart(columnNames5, GenericChart.CHART_TYPE_LINE, "chart_div5", GenericChart.TITLE_TYPE_DATE);
		
		for(int i = 1; i < 30; i++) {
			graphView.addDatum("chart_div5", new Date(2012, 2, i), "Website One", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div5", new Date(2012, 2, i), "Website Two", (float) random.nextInt(28) + 2);
			graphView.addDatum("chart_div5", new Date(2012, 2, i), "Website Three", (float) random.nextInt(28) + 2);
		}
		
		options = graphView.getOptions("chart_div5");
		options.setTitle("Example 5");
		options.hAxis.setTitle("Date");
		options.vAxis.setTitle("Performance");
		options.hAxis.titleTextStyle.setColor("#ff0000");
		options.vAxis.setFormat(ChartOptions.FORMAT_PERCENTAGE_WITH_COMMA);
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);
		
		// Graph6
		String[] columnNames6 = new String[]{ "Data" };
		graphView.addGenericChart(columnNames6, GenericChart.CHART_TYPE_PIE, "chart_div6", GenericChart.TITLE_TYPE_STRING);
		
		graphView.addDatum("chart_div6", "Website Two", "Data", 1.2f);
		graphView.addDatum("chart_div6", "Website One", "Data", 7.0f);
		graphView.addDatum("chart_div6", "Website Three", "Data", 5.0f);
		
		options = graphView.getOptions("chart_div6");
		options.setTitle("Comparative Website Profits");
		options.setHeight(ChartOptions.HALF_PAGE_WIDTH);

		
		// Graph7
		String[] columnNames7 = new String[]{ "Popularity" };
		graphView.addGenericChart(columnNames7, GenericChart.CHART_TYPE_GEOCHART, "chart_div7", GenericChart.TITLE_TYPE_STRING);
		
		graphView.addDatum("chart_div7", "London", "Popularity", 400.0f);
		graphView.addDatum("chart_div7", "Basingstoke", "Popularity", 600.0f);
		graphView.addDatum("chart_div7", "Hull", "Popularity", 900.0f);
		graphView.addDatum("chart_div7", "Edinburgh", "Popularity", 200.0f);
		graphView.addDatum("chart_div7", "Cardiff", "Popularity", 350.0f);
		graphView.addDatum("chart_div7", "Ayr", "Popularity", 1200.0f);
		graphView.addDatum("chart_div7", "Glasgow", "Popularity", 350.0f);
		graphView.addDatum("chart_div7", "Newcastle", "Popularity", 1900.0f);
		graphView.addDatum("chart_div7", "Land's End", "Popularity", 350.0f);
		graphView.addDatum("chart_div7", "Liverpool", "Popularity", 1000.0f);
		
		options = graphView.getOptions("chart_div7");
		options.setTitle("Example 7");
		options.setDisplayMode("markers");
		options.setRegion("GB");
		options.colorAxis.addColor("#ff0000");
		
		// Graph8
		String[] columnNames8 = new String[]{ "Test2" };
		graphView.addGenericChart(columnNames8, GenericChart.CHART_TYPE_SPARK_LINE, "chart_div8", GenericChart.TITLE_TYPE_EMPTY);
		
		graphView.addDatum("chart_div8", "Test2", 600.0f);
		graphView.addDatum("chart_div8", "Test2", 900.0f);
		graphView.addDatum("chart_div8", "Test2", 350.0f);
		graphView.addDatum("chart_div8", "Test2", 631.0f);
		graphView.addDatum("chart_div8", "Test2", 1510.0f);
		graphView.addDatum("chart_div8", "Test2", 1420.0f);
		graphView.addDatum("chart_div8", "Test2", 910.0f);
		
		options = graphView.getOptions("chart_div8");
		options.setTitle("Example 8");
		options.legend.setPosition("left");
		options.addColor("#ff000f");
		options.setAreaOpacity(0.0f);
		
		// Graph11
		String[] columnNames11 = new String[]{ "Test1" };
		graphView.addGenericChart(columnNames11, GenericChart.CHART_TYPE_SPARK_LINE, "chart_div11", GenericChart.TITLE_TYPE_EMPTY);
		
		graphView.addDatum("chart_div11", "Test1", 400.0f);
		graphView.addDatum("chart_div11", "Test1", 200.0f);
		graphView.addDatum("chart_div11", "Test1", 1200.0f);
		graphView.addDatum("chart_div11", "Test1", 1230.0f);
		graphView.addDatum("chart_div11", "Test1", 150.0f);
		graphView.addDatum("chart_div11", "Test1", 120.0f);
		graphView.addDatum("chart_div11", "Test1", 400.0f);
		
		options = graphView.getOptions("chart_div11");
		options.setTitle("Example 11");
		options.legend.setPosition("left");
		options.addColor("#0f00ff");
		options.setAreaOpacity(0.0f);
	        
        graphView.drawCharts();
	}
}