# public class StringImploder #

## Introduction ##

This class exists in order to simplify a process that crops up in numerous places across the application. This class creates an array of strings, and then makes a large comma- or semicolon-separated string upon calling `toString()`. This is useful, for example, to turn an array of strings into a series of calls to JavaScript functions, like so:
```
createNewDivs(...); drawVisualizationString(...); drawVisualizationString(...)
```

This is similar to the behaviour of the [implode(...) method in PHP](http://php.net/manual/en/function.implode.php) (a server-side scripting language).

This class is not intended for use outside of this package, although you are free to take the code if you so wish. It's nothing fancy, it's just mildly convenient for simplifying (and thereby reducing errors in) a number of similar tasks that exist throughout my project.


---


<br />

## Example Usage ##

```
StringImploder sI = new StringImploder();
sI.addNewString("'John Cleese'");
sI.addNewString("'Michael Palin'");
sI.addNewString("'Graham Chapman'");
sI.addNewString("'Terry Jones'");
sI.addNewString("'Eric Idle'");
sI.addNewString("'Terry Gilliam'");

String implodedString = "[" + sI + "]";
// implodedString is now equal to the string:
// ['John Cleese', 'Michael Palin', 'Graham Chapman', 'Terry Jones', 'Eric Idle', 'Terry Gilliam']
```

This string (the value of `implodedString`) could be very useful when calling functions in JavaScript, since it could represent a JSON array.

Why don't I just use Gson? Well, firsly, this solution is very lightweight. Secondly, this solution is more easily applied to different parts of the application, since different parts of the application require strings formatted in different ways. For example, sometimes the string needs to be a JSON object (like `{...}`) and sometimes needs to be a JSON array (like `[...]`). Sometimes the strings need to be separated by commas and sometimes the string need to be separated by semicolons (when calling multiple JavaScript functions in one line).


---


<br />

## Variables ##

```
protected ArrayList<String> strings
```
This ArrayList holds all of the strings that will be iterated through in `toString()`. Strings can only be added to this ArrayList using the `addNewString(...)` method.


---


```
protected String delimiter
```
The delimiter determines in what manner the list items should be separated in the `toString()` call. By default, the list items will be comma-separated, hence the value of delimeter is `", "` (the space is added to make the outputted string slightly more readable.


---


<br />

## Methods ##

```
protected StringImploder(String delimiter)
```
This constructor assigns the local value of `delimiter` to the `delimiter` parameter. It also instantiates the `strings` ArrayList.


---


```
protected StringImploder()
```
This constructor simply calls `this(", ")`, thereby doing the exact same things as the other constructor, but without requiring that a value of `delimiter` is supplied. This acts as a default value for `delimiter`.


---


```
protected void addNewString(String newString)
```
This method simply adds the `newString` parameter to the `strings` ArrayList.


---


```
public String toString()
```
This method overwrites the standard `toString()` behaviour of classes. This method now iterates through the `strings` ArrayList, creating a big string with each value separated from the preceding and succeeding values by the value of `delimiter`. For example usage of this, please view the section at the top of the page.