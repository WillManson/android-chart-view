# Help Page - Dimensions #

## Introduction ##

This page should help clear up any confusion relating to dynamic chart widths/heights within the application.


---


<br />

## "Why is this 'peculiar behaviour' required?" ##

Well, sometimes calling `getWidth()` or `getHeight()` on a WebView can return `0`, which would mess up any dynamic chart dimensions. For example, an application has set the value of `HALF_PAGE_WIDTH` as a certain chart's width. Each time `getDrawVisualization(...)` is called upon the chart, the chart's width is going to be calculated on the spot. OK, so it takes the WebView (or ChartView, if you prefer), upon which is calls `getWidth()`. Now, most of the time, this would be fine. Very occasionally, however, `0` is returned, and this would just mess up everything.

Instead, I have created a workaround. The WebView class has an `onSizeChanged()` method, which is called every time when the size of the WebView changes (as you probably guessed). How does this help? Well, I'll explain below.


---


<br />

## The Implemented Workaround ##

The story begins with the `onSizeChanged()` method, mentioned above. The ChartView overwrites this method. First of all, it checks that both width and height are non-zero (yes, frustratingly, these values can be zero here, too). Provided the values are non-zero, the ChartView instance saves the new dimensions to local values:

```
currentWidth = width;
currentHeight = height;
```

The method then calls `drawCharts()`, which causes the whole WebView (or ChartView, if you prefer) to be redrawn. The key line within the mishmash of code within `drawCharts()` is:

```
sI.addNewString(genericChart.getDrawVisualizationString(currentWidth, currentHeight));
```

Now, don't focus too much on the outermost method call. Instead, look at the `genericChart.getDrawVisualizationString(currentWidth, currentHeight)` part. Let's give the GenericChart class a visit:

```
protected String getDrawVisualizationString(int actualWebViewWidth, int actualWebViewHeight) {	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	options.setDimensions(actualWebViewWidth, actualWebViewHeight);
	String optionsJson = gson.toJson(options);
		
	// Code snipped for readability

	return returnString;
}
```

Now, before `gson.toJson(options)` is called, there is the vital line: `options.setDimensions(actualWebViewWidth, actualWebViewHeight);`. Let's visit the ChartOptions class to see what's going on.

```
protected void setDimensions(int width, int height) {
	chartViewWidth = width;
	chartViewHeight = height;
	
	this.width = getWidth();
	this.height = getHeight();
}
```

So, the ChartOptions instance saves the new width and height values to local variables. From there, the class is able to call `getWidth()` and `getHeight()`, and save the return values to the local `width` and `height` variables.

**NOTE:** _getWidth()_ and _getHeight()_ return dynamic values in accordance with the values of _chartViewWidth_ and _chartViewHeight_.


---


<br />

## "So what's different about the setter and getter methods for the ChartOptions' width and height variables?" ##

Well, since the ChartView's width and height values may change several times during the course of the program's life, the return values of _getWidth()_ and _getHeight()_ need to be ever-changing. These methods are **not** simply returning the values of _width_ and _height_. Instead, these methods do some computations based on the value _widthSpecialSetting_ and _heightSpecialSetting_ (which are set by _setWidth()_ and _setHeight()_, respectively), as well as the values of _chartViewWidth_ and _chartViewHeight_.

When _setWidth(...)_ or _setHeight(...)_ is called, the ChartOptions instance simply saves the value of the parameter to a local variable. Take the _setWidth()_ method, for example:

```
public void setWidth(int width) {
	widthSpecialSetting = width;
}
```

Whereas things are way more active in the _getWidth()_ and _getHeight()_ methods. Take the _getWidth()_ method, for example:

```
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
```