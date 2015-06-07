# public static class ChartOptions.[Legend](Legend.md) #

## Introduction ##

**NOTE:** This inner class is **not** relevant for ChartOptions objects owned by [GeoCharts](GeoChart.md).

This inner class of ChartOptions exists to allow the application controlling the ChartView to dictate the positioning of the chart's legend.


---


<br />

## Variables ##

```
@Expose private String position
```
This variable is used to describe where the legend of the chart should be positioned. See Google's documentation of the [Area Chart](https://developers.google.com/chart/interactive/docs/gallery/areachart#Configuration_Options) to learn what kind of strings may be used here. In the table row for 'legend.position', there are several options for the positioning of the legend (for example "left" or "none").

The @Expose annotation on this variable simply means that it will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public void setPosition(String position)
public String getPosition()
```
These are simple getter and setter methods that behave exactly as you would expect.