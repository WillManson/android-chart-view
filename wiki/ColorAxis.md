# public static class ChartOptions.ColorAxis #

## Introduction ##

**NOTE:** This inner class is only relevant for ChartOptions objects owned by [GeoCharts](GeoChart.md).

This inner class of ChartOptions exists to allow the application controlling the ChartView to define specific colour options for a given chart. This is used to define what colour should denote a 'high-scoring' area on the map. Similarly, it may be used to define what colour should denote a 'low-scoring' area on the map.


---


<br />

## Example Usage ##

As an example, imagine you wanted for a GeoChart to display population data. In this situation, perhaps you would like for densely populated areas to be shaded red, and for sparsely populated areas to be shaded blue. In such a case, you would make the following calls upon the ChartOptions object (let's assume, for the code below, that the variable `chartOptions` references the ChartOptions object in question):
```
chartOptions.colorAxis.addColor("blue");
chartOptions.colorAxis.addColor("red");

// Or alternatively you could call:
// chartOptions.colorAxis.addColor("#0000ff");
// chartOptions.colorAxis.addColor("#ff0000");
```


---


<br />

## Variables ##

```
@Expose private ArrayList<String> colors
```
This variable references the collection of colours that will be used to define the colour axis of the GeoChart in question.

The @Expose annotation on this variable simply means that it will be included in the Gson serialisation.


---


<br />

## Methods ##

```
public void addColor(String color)
public void addColors(Collection<String> colors)
public void removeAllColors()
public ArrayList<String> getColors()
```
These are methods are fairly descriptive and hence shouldn't require any explanation. It is worth mentioning, however, that all of these methods (save the fourth and final one) check to ensure that `colors` is not equal to **null**. If `colors` is, indeed, equal to **null**, then `instantiateColors()` is called.


---


```
private void instantiateColors()
```
This method, as you can probably work out, instantiates the `colors` ArrayList.

Why doesn't this happen in the class' constructor? Well, with instantiation in the constructor, it is possible for an empty array to appear in the final JSON string (when the object is serialised by Gson). This causes problems in the JavaScript, since Google Charts attempts to set the chart's colour axis values to an empty array of strings. This usually causes an error message to be displayed in the ChartView and generally just makes everyone unhappy.