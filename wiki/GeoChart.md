# public class GeoChart extends GenericChart #

## Introduction ##

This class is a subclass of GenericChart, and hence it inherits all of GenericChart's variables and methods.

This class does not current add any methods or variables. It does, however, override a couple of methods, as well as slightly altering the constructor.


---


<br />

## Methods ##

```
public GeoChart(ChartView chartView, String[] columnNames, String targetID, int titleType)
```
The constructor method is almost a direct call to super(...), except that GeoChart's constructor does not take a `chartType` parameter. For GeoChart, `chartType` will always be `GenericChart.CHART_TYPE_GEO`, and hence the super(...) call will always use `GenericChart.CHART_TYPE_GEOCHART` as an argument whether you like it or not. It wouldn't make sense to do otherwise.


---


```
public String getChartColumns()
```
This method does a very similar to its superclass' counterpart, however it wraps the final string in double square brackets, as opposed to single curly brackets. The JavaScript implementation of the Spark Line Chart ends up **slightly** different to the rest of the charts. As a result of this, there are slightly different requirements for the arguments supplied to the `drawVisualization(...)` JavaScript function, hence the different between the `[[...]]` (in the subclass) and the `{...}` (in the superclass).