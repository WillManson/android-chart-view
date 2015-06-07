# public static class ChartOptions.[Axis](Axis.md) #

## Introduction ##

**NOTE:** This inner class is only relevant for ChartOptions objects owned **directly** by [GenericCharts](GenericChart.md) (and not by subclasses thereof).

This inner class of ChartOptions exists to allow the application controlling the ChartView to define specific options for the axes of a given chart. For example, an instance of Axis could let you control the title of the vertical axis.


---


<br />

## Variables ##

```
@Expose private String title
@Expose private String format
@Expose private boolean logScale
```
These variables are fairly straightforward to understand. Their values are established by setter methods. These variables directly related to the final outputted JSON string, therefore each of these variables relates to an option for Google Chart Tools. Take the options for [Area Chart](https://developers.google.com/chart/interactive/docs/gallery/areachart#Configuration_Options), for example.

The @Expose annotation on these variables simply means that they will be included in the Gson serialisation.


---


```
@Expose TitleTextStyle titleTextStyle
```
This is a reference to an object of the TitleTextStyle inner class.

The @Expose annotation on this variable simply means that it will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public Axis()
```
The constructor of this class instantiates `titleTextStyle`.


---


```
public void setTitle(String title)
public String getTitle()
public void setFormat(String format)
public String getFormat()
public void setLogScale(boolean logScale)
public boolean getLogScale()
```
These are simple getter and setter methods that behave exactly as you would expect.