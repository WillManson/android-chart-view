# public class GenericChart #

## Introduction ##

ChartView creates an object of this class for each new chart that is going to created and eventually displayed.


---


<br />

## Constants ##

```
public final static int TITLE_TYPE_DATE = 0
public final static int TITLE_TYPE_STRING = 1
public final static int TITLE_TYPE_INT = 2
public final static int TITLE_TYPE_EMPTY = 3
```
These constants are used to identify what kind of title each row will have. For example, you want to display amount of money made by a product over a period of time, in which case you would choose for the row title type to be `TITLE_TYPE_DATE`. `TITLE_TYPE_EMPTY` is only really useful for sparkline charts.


---


```
public final static String CHART_TYPE_AREA = "areachart"
public final static String CHART_TYPE_SCATTER = "scatterchart"
public final static String CHART_TYPE_VERTICAL_BAR = "columnchart"
public final static String CHART_TYPE_HORIZONTAL_BAR = "barchart"
public final static String CHART_TYPE_LINE = "linechart"
public final static String CHART_TYPE_PIE = "piechart"
public final static String CHART_TYPE_INTENSITY = "intensitymap"
public final static String CHART_TYPE_GEOCHART = "geochart"
public final static String CHART_TYPE_SPARK_LINE = "sparklinechart"
```
These constants are used to identify which kind of chart is to be displayed.


---


<br />

## Variables ##

```
protected ArrayList<Row> rows
```
This is used to store all of the rows of the chart.


---


```
protected ArrayList<String> columnNames
```
This is used to store all of the names of the columns of the chart.


---


```
protected String targetID
```
This is used to store the ID of the div HTML element in which the chart will be displayed. Before the charts are drawn on the page, a JavaScript call will be performed in order to create a div element for each and every GenericChart owned by the ChartView. Each div element's id property will be set to the respective value of `targetID`.


---


```
protected String chartType
```
This is used to store the type of chart to be displayed (assuming this object has been instantiated by a co-operative application, this will always be one of the `CHART_TYPE...` constants).


---


```
protected int emptyCounter
```
This variable is only ever used if `titleType` is assigned the value of `TITLE_TYPE_EMPTY`. This variable is incremented each time a new piece of data is added. What's the point of this variable? Well, it is used so that each row has a different row title, so as to not unintentionally overwrite existing data.


---


```
protected final int titleType
```
This is used to store what type of row title will be used in the chart (see `TITLE_TYPE...` constants group description for explanation). This will be set in the constructor and never changed thereafter, hence it is 'final'.


---


```
protected ChartView chartView
```
This is used to store the ChartView that looks after the GenericChart in question (this is the GenericChart's container).


---


```
protected ChartOptions options
```
This is used to store the options of the GenericChart. These options determine things such as the height, the width and the colours of the displayed chart.


---


```
protected boolean includeZeros
```
This boolean variable determines whether or not you wish to include data on the chart which is equal to `0`. The value of this variable is assigned during the constructor, although may be changed using the `setIncludeZeros()` method. The default value of this variable depends upon which constant you have provided as `chartType`: `includeZeros` is set to true for all charts except `CHART_TYPE_SCATTER`, since otherwise the scatter chart looks stupid.


---


<br />

## Methods ##

```
public GenericChart(WebView webView, String[] columnNames, String chartType, String targetID, int titleType)
```
The constructor of GenericChart is fairly basic. It simply assigns some of the parameters of the method to local variables. On top of this, it instantiates `rows` and `columnNames` (the local `columnNames`). See the variables section of this class to see an explanation of each of these parameters.


---


```
public void addNewColumnNames(String[] newColumnNames)
public void addNewColumnNames(Collection<String> newColumnNames)
public void addNewColumnName(String newColumnName)
```
These methods are used to add new column names to the object's `columnNames` ArrayList. Column names may be added at any stage in the application prior to displaying the charts.


---


```
public boolean addDatum(String rowTitle, String columnName, float datum)
public boolean addDatum(Date rowTitle, String columnName, float datum)
public boolean addDatum(int rowTitle, String columnName, float datum)
public boolean addDatum(String rowTitle, float datum)
```
These methods are used to add data to the chart. The decision of which method to call relies upon which column has been used for the variable `titleType`. The fourth method above is only used when `TITLE_TYPE_EMPTY` has been selected.

This method returns true if and only if the datum has been successively added to the appropriate Row. If `false` is ever returned, it is likely that you have called the wrong method for your `titleType`. For example, if you have set `TITLE_TYPE_DATE` as your `titleType` then you may ever call the second method (of the above methods) on this chart.

All of these methods call a fifth addDatum method (see below).


---


```
private boolean addDatum(String rowTitle, String columnName, float datum, long comparableNumber)
```
This method is only ever called from within the class, hence it is `private`. This method will check whether there is already a row in existence that uses the parameter `rowTitle` as its name. If there is, it will add this datum to the row (whether that means overwriting an existing piece of data or not). If there is not, a new row will be created (using the `addRow` method), before calling itself (`addDatum`) again.

All other `addDatum` methods (see above) will call this method, but first they will all compute what the value for `comparableNumber` should be. `comparableNumber` is vital when sorting the rows into a sensible order.

Here is an overview of how the value of `titleType` will affect what value will be submitted as `comparableNumber`:
  * Using `TITLE_TYPE_STRING` means only `1` will be ever submitted. Rows grouped by _TITLE\_TYPE\_STRING` do not need to be sorted since data are discrete.
  * Using `TITLE_TYPE_DATE` means the millisecond value of the Date object acting as the row name will be submitted. This will ensure that rows are always sorted in chronological order.
  * Using `TITLE_TYPE_INT` means the value of the row name, itself, will be submitted.
  * Using `TITLE_TYPE_EMPTY` means only `1` will be ever submitted (as with `TITLE_TYPE_STRING`)._


---


```
private Row addRow(String rowTitle, long comparableNumber)
```
This method creates a row will the provided `rowTitle` and `comparableNumber`, adds it to `rows` and then returns it.


---


```
public Row getRow(String rowTitle)
```
This method searches through `rows` in an attempt to find a row that has a row name equal to the parameter `rowTitle`. If a row is found, the method will return it, else it will return null.


---


```
protected String getChartRows()
```
This method iterates through all of the rows of the chart, formatting each one in turn. This method is important for the `getDrawVisualizationString(...)` method (when drawing the charts). This method returns a string representing a JSON array. This array contains a number of arrays, each of which represents a single row of the chart's `rows` ArrayList.

The returned string will look something like this:
```
[[new Date(2008, 6, 3), 1414, 1618, 42], [new Date(2008, 6, 4), 1414, 1618, 1234], [new Date(2008, 6, 5), 2718, 42, 3141]]
```

This class makes use of my StringImploder class to quickly and efficiently format the JSON array of arrays (the return string).


---


```
protected String getChartColumns()
```
This method iterates through all of the columns of the chart (`columnNames`), formatting each one in turn. Each column is represented as a string like such: `"'Title':'type'"`. All of these formatted strings end up in one big string together, which represents a JSON object. This string is returned, and plays a key role in the `getDrawVisualizationString(...)` method.

The returned string will look something like this:
```
{'Rowtitle':'date', 'Area':'number', 'Population':'number', 'GDP per capita':'number'}
```

This class makes use of my StringImploder class to quickly and efficiently format the JSON array of arrays (the return string).


---


```
protected String getDrawVisualizationString(int actualWebViewWidth, int actualWebViewHeight)
```
This method creates and returns a string that represents a JavaScript call. This JavaScript call will be injected into the browser and will allow for the chart to be drawn. The `drawVisualization(...)` JavaScript function takes five arguments:
  * Chart type
  * Target ID (the div to which the chart will be drawn - this div will automatically be generated for you, don't worry about it)
  * Details of the columns of the chart (the names and types of the columns) - this is a JSON object which is obtained by calling the `getChartColumns()` method
  * Rows of the chart - this is a JSON array which is obtained by calling the `getChartRows()` method
  * Options of the chart (such as height and title) - this is a JSON object which is obtained by passing the object's ChartOptions instance into a Gson object (which happens within this method)

During this method, the method `setDimensions(...)` is called upon the ChartOptions instance. For details of what's going on here, I will refer you to HelpPageDimensions.


---


```
public void setTargetID(String targetID)
public String getTargetID()
public void setChartType(String chartType)
public String getChartType()
public void setOptions(ChartOptions options)
public ChartOptions getOptions()
public void setIncludeZeros(boolean includeZeros)
public boolean getIncludeZeros()
```

These are simple getter and setter methods that behave exactly as you would expect.