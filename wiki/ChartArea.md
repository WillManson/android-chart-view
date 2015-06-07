# public static class ChartOptions.ChartArea #

## Introduction ##

This inner class of ChartOptions exists to allow the application controlling the ChartView to dictate the size of a given chart.


---


<br />

## Variables ##

```
@Expose private String width
@Expose private String height
```
These variables are used to dictate the size of the chart. They may be set to a dynamic value like `"50%"` or a specific, static number of pixels like `"500"`.

The @Expose annotation on these variables simply means that they will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public void setWidth(String width)
public String getWidth()
public void setHeight(String height)
public String getHeight()
```
These are simple getter and setter methods that behave exactly as you would expect.