# public class ChartOptions #

## Introduction ##

This is a POJO (Plain Old Java Object) that is used by GenericChart (and its subclasses). When the GenericChart needs to be displayed, it is passed into a Gson object in order to create a JSON string.

Most of this class is simply setter and getter methods.


---


<br />

## Constants ##

```
public transient static final int FULL_PAGE_WIDTH = -1
public transient static final int FULL_PAGE_HEIGHT = -2
public transient static final int HALF_PAGE_WIDTH = -3
public transient static final int HALF_PAGE_HEIGHT = -4
```
These constants may be passed into the `setHeight()` and `setWidth()` methods in order to set dynamic widths/heights. The meaning of each one of these constants is quite simple. For example, calling `setWidth(FULL_PAGE_WIDTH)` will mean the chart in question will always fill the ChartView horizontally, regardless of the resolution of the screen, the density of the screen or the orientation of the screen.

**NOTE:** All of these constants may be used for either of the two aforementioned methods (i.e. `FULL_PAGE_WIDTH` is not only just for `setWidth()`, just as `HALF_PAGE_HEIGHT` is not only just for `setHeight()`).


---


```
public transient static final String FORMAT_PERCENTAGE = "#%"
public transient static final String FORMAT_PERCENTAGE_WITH_COMMA = "#,###%"
public transient static final String FORMAT_DOLLAR = "$#"
public transient static final String FORMAT_DOLLAR_WITH_COMMA = "$#,###"
public transient static final String FORMAT_POUND = "£#"
public transient static final String FORMAT_POUND_WITH_COMMA = "£#,###"
public transient static final String FORMAT_EURO = "€#"
public transient static final String FORMAT_EURO_WITH_COMMA = "€#,###"
```
These constants may be passed into the `vAxis.setFormat()` and `hAxis.setFormat()` methods in order to set the formats of the axes of the displayed chart.

These constants are offered as convenience; you are allowed to use your own formatting string, provided it is in accordance with the ICU pattern set (see 'Special Pattern Characters' at [ICU](http://icu-project.org/apiref/icu4c/classDecimalFormat.html#_details)).


---


<br />

## Variables ##

```
private int toRoundTo
```
The number of decimal places to which to round each piece of row data. By default, `toRoundTo` is set to `-1`, meaning that no rounding will take place.


---


```
private int widthSpecialSetting
private int heightSpecialSetting
```
These store what value the user has directly set for the width. For example, if the user has called `options.setWidth(FULL_PAGE_WIDTH)`, the value of widthSpecialSetting will become `-1`, and **it will remain `-1` until `options.setWidth(...)` is called again**.

The object's `width` and `height` values are set automatically, and this only ever happens directly before displaying the chart, in order to maintain dynamic values (if appropriate).

See HelpPageDimensions for more information.


---


```
private int chartViewWidth
private int chartViewHeight
```
These values keep track of the width and height of the ChartView that contains the GenericChart to which the ChartOptions is related. These are set via the `setActualChartViewDimensions()` method (which is, in turn, called by the `setDimensions()` method).

See HelpPageDimensions for more information.


---


```
@Expose private String title
@Expose private String region
@Expose private String displayMode
@Expose private ArrayList<String> colors
@Expose private int width
@Expose private int height
@Expose private boolean enableInteractivity
@Expose private float areaOpacity
@Expose private String backgroundColor
```
These variables are fairly straightforward to understand. Their values are established by setter methods (with the exception of `colors`, which can be set by using a combination `addColor()` and `addColors()` methods). These variables directly related to the final outputted JSON string, therefore each of these variables relates to an option for Google Chart Tools. Take the options for [Area Chart](https://developers.google.com/chart/interactive/docs/gallery/areachart#Configuration_Options), for example.

The @Expose annotation on these variables simply means that they will be included in the Gson serialisation.


---


```
@Expose public Axis vAxis
@Expose public Axis hAxis
@Expose public Legend legend
@Expose public ColorAxis colorAxis
```
These are references to objects of inner classes. See the following pages for an explanation of what each object does: [Axis](Axis.md), [Legend](Legend.md) and ColorAxis.

The @Expose annotation on these variables simply means that they will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public ChartOptions()
```
The constructor of `ChartOptions` is pretty boring: it just initialises the references to objects of inner classes (see above) and assigns default values to a few variables:
  * `areaOpacity` is set to `0.3f`.
  * `enableInteractivity` is set to `true`.
  * `toRoundTo` is set to `-1`.


---


```
public void addColor(String color)
public void addColors(Collection<String> colors)
public void removeAllColors()
public ArrayList<String> getColors()
private void instantiateColors()
```
These methods interact with `colors`. They all behave exactly as expected. `instantiateColors()` instantiates the `colors` ArrayList, since it is not instantiated until the first colour is added.

Why isn't instantiateColors() called in the class' constructor? Well, with instantiation in the constructor, it is possible for an empty array to appear in the final JSON string (when the object is serialised by Gson). This causes problems in the JavaScript, since Google Charts attempts to set the chart's colour axis values to an empty array of strings. This usually causes an error message to be displayed in the ChartView and generally just makes everyone unhappy.

When adding colours to the array, you can use any string that would be valid for a typical HTML element. This means you should use strings like `"#14f13a"` or `"blue"`.


---


```
public void setHeight(int height)
public void setWidth(int width)
```
The `setHeight(...)` method sets `heightSpecialSetting` to the `height` parameter. In an identical manner, the `setWidth(...)` method sets `widthSpecialSetting` to the `width` parameter.

See HelpPageDimensions for insight into what exactly is going on here.


---


```
public int getHeight()
public int getWidth()
```
These methods return the actual height and width of the chart that is to be displayed. If it has been set for the width or height to be dynamic (for example, if `widthSpecialSetting` has been set to `FULL_PAGE_WIDTH`), then the actual dimension to be used will be computed and returned here. This is done by using the values of `chartViewWidth` and `chartViewHeight`.

See HelpPageDimensions for insight into what exactly is going on here.


---


```
protected void setDimensions(int width, int height)
```
This method is always called during the `getDrawVisualizationString(...)` method in the GenericChart class. This method takes the two parameters and assigns them to `chartViewWidth` and `chartViewHeight`, respectively. The method then assigns the local `width` and `height` variables to the return values of `getWidth()` and `getHeight()` respectively. This is done to make sure that the dimensions of the chart is able to dynamically adapt to the size of the parent ChartView.

See HelpPageDimensions for insight into what exactly is going on here.


---


```
public void setEnableInteractivity(boolean enableInteractivity)
public boolean getEnableInteractivity()
public void setTitle(String title)
public String getTitle()
public void setRegion(String region)
public String getRegion()
public void setDisplayMode(String displayMode)
public String getDisplayMode()
public void setToRoundTo(int places)
public int getToRoundTo()
public void setAreaOpacity(float areaOpacity)
public float getAreaOpacity()
public String setBackgroundColor(String backgroundColor)
public void getBackgroundColor()
```
These are simple getter and setter methods that behave exactly as you would expect.