# Help Page - Example Usage #

## Introduction ##

This page should help give you some insight into the many different ways in which the ChartView package may be used.

This page assumes that you have correctly followed the installation guide (HelpPageInstallation), and have got a working application.


---


<br />

## The XML ##

In order to run the examples on this page, you will need an XML file in the res/layout folder that looks like this:

```
<com.willmanson.chartview.ChartView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/chartview"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

</com.willmanson.chartview.ChartView>
```

For me, I will name this XML file activity\_chart.xml.


---


<br />

## Setup ##

Right, this isn't the full setup procedure (it doesn't cover stuff already covered in HelpPageInstallation). This section briefly covers a few things that you will need to do in order to get the examples on this page working.

Firstly, we'll be needing the assistance of Java's [Date](http://docs.oracle.com/javase/1.4.2/docs/api/java/util/Date.html) class and Java's [Random](http://docs.oracle.com/javase/1.4.2/docs/api/java/util/Random.html) class (since we'll just be randomly generating data for our examples). Therefore it follows that these classes are imported in the application's activity:

```
import java.util.Date;
import java.util.Random;
```

Now, let's move on to the overwritten `onCreate(...)` method in the activity. It begins as you would normally expect:

```
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// I usually like to remove the title of the activity
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	setContentView(R.layout.activity_chart);
```

Now, let's set a couple of variables for later use

```
	ChartView chartView = (ChartView) findViewById(R.id.chartview);		
	Random random = new Random();
```

Now we're all set to begin drawing some charts!


---


<br />

## Example 1 ##

OK, let's start off simple by plotting the sale numbers of three (made-up) products over different dates. First, let's establish the names of the different products:

```
	String[] productNames = new String[]{ "Torricelli's Trumpet", "Turing Machine", "Perpetual Motion Machine" };
```

**NOTE:** Apostrophes are automatically removed from the strings (above), since they cause difficulties when the chart is ultimately displayed. This effect will be visible in the screenshot at the bottom of the article (the chart will use the text "Torricellis Trumpet").

Fantastic, now let's create the chart:

```
	chartView.addGenericChart(productNames, GenericChart.CHART_TYPE_AREA, "product_sales", GenericChart.TITLE_TYPE_DATE);
```

"Wait! Slow down! What are all of these arguments?"

Well, we created the first argument a few moments ago. This argument defines what the name of each 'column' of data should be.

The second argument (`GenericChart.CHART_TYPE_AREA`) defines what type of chart is to be used.

The third argument (`"product_sales"`) is used to give the chart a unique ID. It is later used so that you can identify which of your [GenericCharts](GenericChart.md) to which you want to add new data. It is also used as the id of the div (in the HTML page) in which the chart is eventually rendered. This process is done automatically, however, so no need to worry about this bit.

**NOTE:** The third argument must be a valid HTML id string. If you're not entirely familiar with what this means, just use lower case characters (and nothing else) and you can't go wrong. However, you may not use the string `"graphs"`, since this id already exists within the HTML page and using it will just mess everything up.

The fourth argument (`GenericChart.TITLE_TYPE_DATE`) identifies the way you wish to differentiate different 'rows' of data. In this case, we are seeing how the products fare over time, hence we are interested in differentiating 'rows' of data by date.

OK, now, let's add a whole bunch of random data:

```
for(int i = 1; i < 30; i++) {
	chartView.addDatum("product_sales", new Date(2012, 2, i), "Torricelli's Trumpet", (float) random.nextInt(60) + 100);
	chartView.addDatum("product_sales", new Date(2012, 2, i), "Turing Machine", (float) random.nextInt(60) + 50);
	chartView.addDatum("product_sales", new Date(2012, 2, i), "Perpetual Motion Machine", (float) random.nextInt(60));
}
```

"Slow down again! What are all of these arguments to the `addDatum(...)` method?"

The first argument (`"product_sales"`) is used to establish which of the [GenericCharts](GenericChart.md) we are talking about. A few moments ago, when we created the chart, we used the string `"product_sales"` as the chart's ID, hence we used it here, too.

The second argument (`new Date(2012, 2, i)`) is the date for which we are inputting the data. In the example above, data will eventually have been added for all dates from the 1st to the 29th of March (the month in Date objects begins at `0` for January, hence March relates to a value of `2`).

The third argument (`"Torricelli's Trumpet"`) is used to identify which data 'column' we are referring to. In the example above, we add a piece of random data for each of the three product names (as established a few code blocks ago).

The fourth argument (`(float) random.nextInt(100)`) is the piece of data, itself. In this case, we are randomly generating our own (since we don't have any real data).

Fantastic, we're almost there! Now, the chart is going to be pretty meaningless unless we change the way is looks slightly. First of all, acquire the chart's ChartOptions instance:

```
	ChartOptions productSalesOptions = chartView.getOptions("product_sales");
```

Again, we are using the GenericChart's value of `targetID`.

Now, let's just changes a few options for the chart:

```
	productSalesOptions.setTitle("Product Sales in March 2012");
	productSalesOptions.hAxis.setTitle("Date");
	productSalesOptions.vAxis.setTitle("Number of Sales");

	// Normally, area charts will look a bit strange when the
	// device is landscape, since the chart gets squashed vertically.
	//
	// Instead, setting the height of the chart to be half of
	// the width of the page can be a nice solution, since it
	// keeps the width-to-height ratio sensible, regardless of 
	// the device's orientation.
	productSalesOptions.setHeight(ChartOptions.HALF_PAGE_WIDTH);
```

Fantastic! It looks like we're done with this chart.

Wait! I would like to have another chart. This time, I want to display a chart to show how well the Turing Machine is selling across the world. Easy! Let's create a new chart!

```
	String[] globalSales = new String[]{ "Number of Sales" };
	chartView.addGenericChart(globalSales, GenericChart.CHART_TYPE_GEOCHART, "global_sales", GenericChart.TITLE_TYPE_STRING);
```

OK, now, let's add some data.

```
	chartView.addDatum("global_sales", "Russia", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "France", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "Japan", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "Spain", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "Brazil", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "South Africa", "Number of Sales", (float) random.nextInt(100));
	chartView.addDatum("global_sales", "Latvia", "Number of Sales", (float) random.nextInt(100));
```

You may also use strings like `"FR"`, `"RU"` and `"RU"` ([ISO 3166-1 codes](http://en.wikipedia.org/wiki/ISO_3166-1)) to identify the countries.

The chart will highlight the areas of a high number of sales with the colours green. "I don't really like green!" OK, let's change it to red.

```
	ChartOptions globalSalesOptions = chartView.getOptions("global_sales");
	globalSalesOptions.colorAxis.addColor("#ff0000");
```

Now, let's draw all of the charts.

```
	chartView.drawCharts();
}
```

We're done! It looks great!

Here is a screenshot of the charts working on a Nexus 7:

![http://i.imgur.com/UcDdp.png](http://i.imgur.com/UcDdp.png)

The charts look especially great when the device is held horizontally:

![http://i.imgur.com/jnCj4.png](http://i.imgur.com/jnCj4.png)

![http://i.imgur.com/nILt5.png](http://i.imgur.com/nILt5.png)

The charts also display well on other devices. Here's one of the charts displayed on a Samsung Galaxy S3:

![http://i.imgur.com/vmdVe.png](http://i.imgur.com/vmdVe.png)


---


<br />

## Example 2 ##

Now that you're comfortable with how to use ChartView, let's just go through a few more examples to showcase some useful functionality.

Let's pretend that you own a supermarket. You want to draw a (vertical) bar chart to see how well various food products sell for different age groups. We will draw a chart that displays what percentage of customers within a given age group bought a given product.

**NOTE:** A value of, say, `0.12f` equates to 12% when plotted on a chart to display percentages.

```
	String[] foodProducts = new String[]{ "Chocolate", "Bread", "Milk", "Crisps" };
	// We will use TITLE_TYPE_STRING since we will be categorising
	// rows in a manner like "16-25", "26-35" etc.
	chartView.addGenericChart(foodProducts, GenericChart.CHART_TYPE_VERTICAL_BAR, "food_products", GenericChart.TITLE_TYPE_STRING);
	
	chartView.addDatum("food_products", "16-25", "Chocolate", 0.513f);
	chartView.addDatum("food_products", "16-25", "Bread", 0.127f);
	chartView.addDatum("food_products", "16-25", "Milk", 0.055f);
	chartView.addDatum("food_products", "16-25", "Crisps", 0.123f);

	chartView.addDatum("food_products", "26-35", "Chocolate", 0.102f);
	chartView.addDatum("food_products", "26-35", "Bread", 0.611f);
	chartView.addDatum("food_products", "26-35", "Milk", 0.812f);
	chartView.addDatum("food_products", "26-35", "Crisps", 0.091f);

	chartView.addDatum("food_products", "36-45", "Chocolate", 0.812f);
	chartView.addDatum("food_products", "36-45", "Bread", 0.611f);
	chartView.addDatum("food_products", "36-45", "Milk", 0.662f);
	chartView.addDatum("food_products", "36-45", "Crisps", 0.584f);

	// etc.
	
	ChartOptions foodOptions = chartView.getOptions("food_products");
	foodOptions.setTitle("Product Sales in March 2012 for Different Age Groups");
	foodOptions.hAxis.setTitle("Age Group");
	foodOptions.vAxis.setTitle("Percentage of Customers Purchasing This Product");
	foodOptions.vAxis.setFormat(ChartOptions.FORMAT_PERCENTAGE);
	foodOptions.setHeight(ChartOptions.HALF_PAGE_WIDTH);

	// To help distinguish the titles of the axes from the rest of 
	// the chart, it may be good idea to change the colour of these
	// titles. This is done like so:
	foodOptions.hAxis.titleTextStyle.setColor("#ff0000");
	foodOptions.vAxis.titleTextStyle.setColor("#ff0000");
```

Fantastic! That was very easy, indeed. After adding all of the charts you want to display, don't forget to call `drawCharts()`.

Here is a screenshot of the chart working on a Nexus 7:

![http://i.imgur.com/eyVi3.png](http://i.imgur.com/eyVi3.png)


---


<br />

## Example 3 ##

Your supermarket has become very popular, so you decided to start selling your food items through a website (somehow). Now, you want to draw a chart to see how well bread is selling in different states of the US.

Easy!

```
	String[] nationalSales = new String[]{ "Sales" };
	chartView.addGenericChart(nationalSales, GenericChart.CHART_TYPE_GEOCHART, "national_sales", GenericChart.TITLE_TYPE_STRING);
	
	chartView.addDatum("national_sales", "Kansas", "Sales", 720.0f);
	chartView.addDatum("national_sales", "Texas", "Sales", 480.0f);
	chartView.addDatum("national_sales", "West Virginia", "Sales", 90.0f);
	chartView.addDatum("national_sales", "Minnesota", "Sales", 160.0f);
	chartView.addDatum("national_sales", "Iowa", "Sales", 910.0f);
	chartView.addDatum("national_sales", "Alaska", "Sales", 120.0f);

	// Whilst not particularly useful in this example, you are able
	// to give the name of specific towns or cities in country (you
	// are not restricted to just using counties/states). For
	// example:
	chartView.addDatum("national_sales", "Boston", "Sales", 260.0f);
	chartView.addDatum("national_sales", "Mountain View", "Sales", 750.0f);
	
	ChartOptions nationalOptions = chartView.getOptions("national_sales");
```

The next couple of lines, below, are very important.

Having a display mode of "markers" means that circles will be placed upon locations to represent the provided values (as opposed to highlighting the regions). Currently, highlighting the regions only really works properly when selecting countries (as opposed to states/counties **within** a country).

The markers placed on the map vary in size according to the provided values. Your intuition would be correct in assuming that the greater the value, the larger the marker.

The markers also vary in colour according to the provided values (as with the chart described in the latter part of 'Example 1' (above)).

```
	nationalOptions.setDisplayMode("markers");
	nationalOptions.setRegion("US");
```

In this case, we are going to set it so that the colours of the markers fall on a spectrum from red to blue according to the provided values (with blue representing a low value and red representing a high value). It is very straightforward to do this:

```
	nationalOptions.colorAxis.addColor("#0000ff");
	nationalOptions.colorAxis.addColor("#ff0000");
```

We're done! Similar code can be used to display data about other countries. For example, you could display data about the UK by providing `"GB"` to the `setRegion(...)` call, and then using values like `"London"`, `"Liverpool"`, `"Newcastle"` and `"Ayr"` for the city names.

After adding all of the charts you want to display, don't forget to call `drawCharts()`.

Here is a screenshot of the chart working on a Nexus 7:

![http://i.imgur.com/XsaZ9.png](http://i.imgur.com/XsaZ9.png)


---


<br />

## Example 4 ##

Pie charts can be quite a simple yet elegant way to display data. Drawing a pie chart with ChartView is incredibly simple.

Let's draw a pie chart to display how well our different food products are doing, comparatively.

```
	String[] pieChartColumn = new String[]{ "Data" };
	chartView.addGenericChart(pieChartColumn, GenericChart.CHART_TYPE_PIE, "sales_pie_chart", GenericChart.TITLE_TYPE_STRING);
	
	chartView.addDatum("sales_pie_chart", "Chocolate", "Data", 261.0f);
	chartView.addDatum("sales_pie_chart", "Bread", "Data", 818.0f);
	chartView.addDatum("sales_pie_chart", "Milk", "Data", 991.0f);
	chartView.addDatum("sales_pie_chart", "Crisps", "Data", 1125.0f);
	
	ChartOptions pieChartOptions = chartView.getOptions("sales_pie_chart");
	pieChartOptions.setTitle("Comparative Product Profits");
	pieChartOptions.setHeight(ChartOptions.HALF_PAGE_WIDTH);
```

**NOTE:** The data we are providing (e.g. `818.0f`) are the actual raw (albeit made-up) data of the product sale numbers. You are **not** providing the percentages. The Google Charts API does all of the computation behind the scenes to decide what percentage each individual pie 'segment' should take of the overall pie.

We're done!

After adding all of the charts you want to display, don't forget to call `drawCharts()`.

Here is a screenshot of the chart working on a Nexus 7:

![http://i.imgur.com/12LAc.png](http://i.imgur.com/12LAc.png)