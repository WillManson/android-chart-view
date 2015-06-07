# public static class ChartOptions.[Axis](Axis.md).TitleTextStyle #

## Introduction ##

**NOTE:** This inner class is only relevant for ChartOptions objects owned **directly** by [GenericCharts](GenericChart.md) (and not by subclasses thereof).

This inner class of ChartOptions.Axis exists to allow the application controlling the ChartView to define the colour of the font of the text label of a chart axis (GenericChart.Axis) of a GenericChart of a ChartView. I know, I know... it sounds incredibly specific, but I have truly found this functionality useful in the past.


---


<br />

## Variables ##

```
@Expose String color
```
This string holds the colour of the font in question. You can use any string that would be valid for a typical HTML element. This means you should use strings like `"#14f13a"` or `"blue"`.

The @Expose annotation on this variable simply means that they will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public void setColor(String color)
public String getColor()
```
These are simple getter and setter methods that behave exactly as you would expect.