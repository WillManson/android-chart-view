package com.willmanson.chartview;

import java.util.ArrayList;
import java.util.Collection;

import android.webkit.WebView;
import com.google.gson.annotations.Expose;

public class ChartOptions {
	public static final int FULL_PAGE_WIDTH = -1;
	public static final int FULL_PAGE_HEIGHT = -2;
	public static final int HALF_PAGE_WIDTH = -3;
	public static final int HALF_PAGE_HEIGHT = -4;
	public static final String FORMAT_PERCENTAGE = "#%";
	public static final String FORMAT_PERCENTAGE_WITH_COMMA = "#,###%";
	public static final String FORMAT_DOLLAR = "$#";
	public static final String FORMAT_DOLLAR_WITH_COMMA = "$#,###";
	public static final String FORMAT_POUND = "£#";
	public static final String FORMAT_POUND_WITH_COMMA = "£#,###";
	public static final String FORMAT_EURO = "€#";
	public static final String FORMAT_EURO_WITH_COMMA = "€#,###";
	private int toRoundTo;
	private int widthSpecialSetting;
	private int heightSpecialSetting;
	private int chartViewWidth;
	private int chartViewHeight;
	
	@Expose private String title, region, displayMode;
	@Expose private ArrayList<String> colors;
	@Expose private int width, height;
	@Expose private boolean enableInteractivity;
	@Expose private float areaOpacity;
	@Expose private String backgroundColor;
	@Expose public Axis vAxis;
	@Expose public Axis hAxis;
	@Expose public Legend legend;
	@Expose public ColorAxis colorAxis;
	@Expose public ChartArea chartArea;
	
	public ChartOptions() {
		vAxis = new Axis();
		hAxis = new Axis();
		legend = new Legend();
		colorAxis = new ColorAxis();
		chartArea = new ChartArea();
		
		setAreaOpacity(0.3f);
		setEnableInteractivity(true);
		setToRoundTo(-1);
	}
	
	public static class Axis {
		@Expose private String title, format;
		@Expose private boolean logScale;
		@Expose TitleTextStyle titleTextStyle;
		
		public Axis() {
			titleTextStyle = new TitleTextStyle();
		}
		
		public static class TitleTextStyle {
			@Expose String color;
			
			public void setColor(String color) {
				this.color = color;
			}
			
			public String getColor() {
				return color;
			}
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setFormat(String format) {
			this.format = format;
		}
		
		public String getFormat() {
			return format;
		}
		
		public void setLogScale(boolean logScale) {
			this.logScale = logScale;
		}
		
		public boolean getLogScale() {
			return logScale;
		}
	}
	
	public static class Legend {
		@Expose private String position;
		
		public void setPosition(String position) {
			this.position = position;
		}
		
		public String getPosition() {
			return position;
		}
	}
	
	public static class ColorAxis {
		@Expose private ArrayList<String> colors;
		
		public void addColor(String color) {
			if(colors == null) instantiateColors();
			colors.add(color);
		}
		
		public void addColors(Collection<String> colors) {
			if(colors == null) instantiateColors();
			this.colors.addAll(colors);
		}
		
		public void removeAllColors() {
			if(colors == null) instantiateColors();
			colors.clear();
			colors = null;
		}
		
		public ArrayList<String> getColors() {
			return colors;
		}
		
		private void instantiateColors() {
			colors = new ArrayList<String>();
		}
	}
	
	public static class ChartArea {
		@Expose private String width, height;
		
		public void setWidth(String width) {
			this.width = width;
		}
		
		public String getWidth() {
			return width;
		}
		
		public void setHeight(String height) {
			this.height = height;
		}
		
		public String getHeight() {
			return height;
		}
	}
	
	public void setEnableInteractivity(boolean enableInteractivity) {
		this.enableInteractivity = enableInteractivity;
	}
	
	public boolean getEnableInteractivity() {
		return enableInteractivity;
	}
			
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	
	public String getDisplayMode() {
		return displayMode;
	}
	
	public void addColor(String color) {
		if(colors == null) instantiateColors();
		colors.add(color);
	}
	
	public void addColors(Collection<String> colors) {
		if(colors == null) instantiateColors();
		this.colors.addAll(colors);
	}
	
	public void removeAllColors() {
		if(colors == null) instantiateColors();
		colors.clear();
		colors = null;
	}
	
	public ArrayList<String> getColors() {
		return colors;
	}
	
	private void instantiateColors() {
		colors = new ArrayList<String>();
	}

	public void setHeight(int height) {
		heightSpecialSetting = height;
	}
	
	public int getHeight() {
		if (heightSpecialSetting == FULL_PAGE_WIDTH)
			return chartViewWidth / 2;
		else if (heightSpecialSetting == FULL_PAGE_HEIGHT)
			return chartViewHeight / 2;
		else if (heightSpecialSetting == HALF_PAGE_WIDTH)
			return chartViewWidth / 4;
		else if (heightSpecialSetting == HALF_PAGE_HEIGHT)
			return chartViewHeight / 4;
		return heightSpecialSetting / 2;
	}
	
	public void setWidth(int width) {
		widthSpecialSetting = width;
	}

	public int getWidth() {
		if (widthSpecialSetting == FULL_PAGE_WIDTH)
			return chartViewWidth / 2;
		else if (widthSpecialSetting == FULL_PAGE_HEIGHT)
			return chartViewHeight / 2;
		else if (widthSpecialSetting == HALF_PAGE_WIDTH)
			return chartViewWidth / 4;
		else if (widthSpecialSetting == HALF_PAGE_HEIGHT)
			return chartViewHeight / 4;
		return widthSpecialSetting / 2;
	}
	
	public void setAreaOpacity(float areaOpacity) {
		this.areaOpacity = areaOpacity;
	}
	
	public float getAreaOpacity() {
		return areaOpacity;
	}
	
	protected void setDimensions(int width, int height) {
		chartViewWidth = width;
		chartViewHeight = height;
		
		this.width = getWidth();
		this.height = getHeight();
	}
	
	public void setToRoundTo(int places) {
		toRoundTo = places;
	}

	public int getToRoundTo() {
		return toRoundTo;
	}
	
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
}