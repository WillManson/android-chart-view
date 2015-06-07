# public class ChartView extends [WebView](http://developer.android.com/reference/android/webkit/WebView.html) #

## Introduction ##

This class is used to look after and add data to the numerous [GenericCharts](GenericChart.md) which the user/application would like to be drawn.


---


<br />

## Variables ##

```
private ArrayList<GenericChart> genericCharts
```
This variable is an ArrayList of all of the object's [GenericCharts](GenericChart.md).


---


```
private int currentWidth 
private int currentHeight
```
These variables are used to keep a tab on what are the exact dimensions of the ChartView in question. These values are only ever set by the `onSizeChanged(...)` method, so as to ensure precision when drawing charts with dynamic dimensions.


---


<br />

## Methods ##

```
public ChartView(Context context, AttributeSet attrs)
```
The constructor of this class simply calls `super(context, attrs)`, before instantiating `genericChart`.


---


```
public void drawCharts()
```
This method does a number of things before loading the .html page. It sets a default zoom distance (to ensure consistency between devices), and also turns JavaScript on in the browser. The method allows the ChartView to accept pinch-to-zoom, whilst removing the zoom buttons on the screen (since I find they detract from the overall feel of the displayed charts).

This method makes the call:
```
loadUrl("file:///android_asset/chartpage.html");
```
As a result of this, it is vital to ensure that you **do** actually have a chartpage.html file in the project's 'assets' folder. This HTML file must be a specific one (you can't simply create an empty HTML file and put it there). I have provided this HTML file alongside the Java classes.

In order to actually display the charts, the method makes two different types of JavaScript injections into the page.

The first is a call to the `insertNewDivs(...)` JavaScript function (declared in the HTML page), which takes an array as a parameter. This array includes the value of `targetID` of each GenericChart in `genericCharts`. The array is used to create a div element in the HTML page for each and every GenericChart.

After this, the method makes a number of JavaScript calls in the form `drawVisualization(...)` (a JavaScript function declared in the HTML page). The JavaScript calls are obtained by calling `getDrawVisualizationString()` on each GenericChart in `genericCharts` in turn.


---


```
protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
```
This method overwrites the standard `onSizeChanged(...)` behaviour (inherited from [View](http://developer.android.com/reference/android/view/View.html)). The method checks whether the new `width` and `height` values are non-zero and, assuming they are, assigns these values to the local variables of `currentWidth` and `currentHeight`. After doing this, the method calls `drawCharts()` to redraw the charts.


---


```
public void addGenericChart(String[] columnNames, String chartType, String targetID, int chartTitleType)
public void addChart(GenericChart genericChart)
```
These methods both serve to add another chart to `genericCharts`. In case of the former of the two methods, it actually instantiates a new GenericChart object (or a subclass thereof, according to the value of the `chartType` parameter), before adding it to the `genericCharts` ArrayList. The latter of the two methods simply adds the `genericChart` parameter to the ArrayList. Whilst I haven't yet found the second method particularly useful, I have included it on the off chance that it may eventually be.


---


```
public GenericChart getGenericChart(String targetID)
```
This method searches through the `genericCharts` ArrayList for a GenericChart whose `targetID` value matches the `targetID` parameter. The method will return the GenericChart, if found, else it will return null.


---


```
public void addDatum(String targetID, Date rowTitle, String columnName, float datum)
public void addDatum(String targetID, int rowTitle, String columnName, float datum)
public void addDatum(String targetID, String rowTitle, String columnName, float datum)
public void addDatum(String targetID, String columnName, float datum)
```
These methods are used to add data to a given chart. Each method begins by locating the desired GenericChart (according to the `targetID` parameter). This is done by calling the `getGenericChart(...)` method. From there, all of the above methods ultimately call one of the `addDatum(...)` methods of GenericChart. As long as the type (date/int/string/empty) of the row title is appropriate for the GenericChart in question, the `datum` parameter will eventually be added to a row in the GenericChart.

The decision of which method to call relies upon which constant has been used for the variable `titleType` in the targeted GenericChart. The fourth method above is only used when `TITLE_TYPE_EMPTY` has been selected.


---


```
public ChartOptions getOptions(String targetID)
```
This is a fairly straightforward method that locates the GenericChart in the `genericCharts` ArrayList whose value of `targetID` matches the `targetID` parameter (using the `getGenericChart(...)` method). If such a GenericChart is found, its ChartOptions object is returned by calling `getOptions()` on the GenericChart, else `null` is returned.