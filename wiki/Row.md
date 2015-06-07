# public class [Row](Row.md) implements [Comparable](http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html) #

## Introduction ##

This class is used to represent a row of data in the chart. A given GenericChart may have many [Rows](Row.md). Each one of these rows represents a set of data attributed to a single row title, which may be a string, an integer or even a date.

The row title is the determining factor in deciding whether or not a new piece of data should be added to a given row. It is then up to looking at the column names to decide whereabouts the supplied datum should be added.

The use of the class isn't expected outside of this charting package, since its functionality is rather specific to the situation. However, do with it as you please!


---


<br />

## Variables ##

```
protected ArrayList<String> columnNames
```
This is a reference to the ArrayList of the names of the various columns in the chart. This gives the row a reference as to where to place data in the `data` ArrayList.


---


```
protected ArrayList<Float> data
```
This ArrayList holds all of the data of the row. Since the data does not include the the row name, all of these values will be floats. This ArrayList is added to via the `addDatum(...)` method.


---


```
protected String printRowName
```
This is the string of the row title as it would be represented in JavaScript. For example, if the row title was a date referencing the 2nd of January 2008, `printRowName` would have a value of:
```
"new Date(2008, 0, 2)"
```

Similarly, if the row title was a string referencing the actor and comedian Stephen Fry, `printRowName` would have a value of:
```
"'Stephen Fry'"
```


---


```
protected long comparableNumber
```
This variable is vital for sorting the rows into a sensible order.

Here is an overview of how the a ChartView's value of `titleType` will affect what the value of `comparableNumber` will be:
  * Using `TITLE_TYPE_STRING` means only `1` will be ever used. Rows grouped by `TITLE_TYPE_STRING` do not need to be sorted since data are discrete.
  * Using `TITLE_TYPE_DATE` means the millisecond value of the Date object acting as the row name will be used. This will ensure that rows are always sorted in chronological order.
  * Using `TITLE_TYPE_INT` means the value of the row name, itself, will be used.
  * Using `TITLE_TYPE_EMPTY` means only `1` will be ever used (as with `TITLE_TYPE_STRING`).


---


<br />

## Methods ##

```
protected Row(String rowName, ArrayList<String> columnNames, long comparableNumber)
```
The constructor of this class is fairly straightforward. It simply assigns all of the parameters to local variables. On top of that, it instantiates the `data` ArrayList and then calls `buildEmptyData()`, which assigns a default value of `0.0f` for each and every column in the table.


---


```
public String toString()
```
The overridden `toString()` method returns `printRowName`.


---


```
protected boolean addDatum(String columnName, float datum)
```
This method iterates through the `columnNames` ArrayList, and checking each string in the ArrayList against the `columnName` parameter. Upon finding the appropriate column (if applicable, see below), the method overwrites the existing piece of data in that position of the `data` ArrayList with the `datum` parameter.

The method returns a boolean value according to whether the datum was successfully added (i.e. it will return false when no value of `columnNames` matches the `columnName` parameter).


---


```
protected String getRowString(int titleType, int toRoundTo, boolean includeRowTitle, boolean includeZeros)
```
This method iterates through all of the data, adding each piece in turn to a StringImploder. Upon finishing the iteration, the method returns a string representing a JSON array. The returned array will look something like this:
```
[new Date(2009, 3, 1), 3.4, 3.1, 2.6]
// ... or...
['Example', 4.1, 5.6, 2.7]
// ... or...
[3, 5.1, 3.8, 1.3]
```

The value of `includeZeros` determines whether `0` or `null` should be placed into the StringImploder in cases where the datum is equal to `0.0f`. This is done since it can sometimes be useful to not plot any values for a given column in a chart. This is especially true in the case of a scatter chart, where including lots of zero values looks very messy. In such a case, `includeZeros` should be set to `false`.


---


```
protected void buildEmptyData()
```
This method sets a default value of `0` to number of values in the `data` ArrayList (according to how many strings there are in `columnNames`). This method is called by the class' constructor.


---


```
public int compareTo(Object object)
```
This method helps a GenericChart to order its rows into a sensible order. To do this, it uses a row's value of `comparableNumber`. Where does this come from? Well, it is calculated by the GenericChart's `addDatum(...)` methods.