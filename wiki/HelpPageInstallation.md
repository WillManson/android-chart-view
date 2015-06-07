# Help Page - Installation Guide #

## Introduction ##

So you've decided that your Android application could do with some graphs and you're ready to install ChartView into your application? Fantastic, you've come to the right place.

First of all, if you haven't already done so, download the .zip or .tar.gz file from the [Downloads](http://code.google.com/p/android-chart-view/downloads/list) section of this mini-website.


---


<br />

## Step 1 ##

Uncompress the file, of course!


---


<br />

## Step 2 ##

Place all of the .java files into somewhere sensible in your /src folder in your Android project. You'll probably want to dump these files in a separate (new) path of folders within the /src folder. Make a note of where you've put these files.

To prevent yourself from having to make any changes to the .java files or the XML document in the future, I suggest that you paste the files in a file path as such: "com/willmanson/chartview".

If you choose to place the files elsewhere, you will need to accordingly change the `package com.willmanson.chartview` part of each .java file.


---


<br />

## Step 3 ##

Put chartpage.html into the /assets folder in your Android project. You are welcome to change the name of this file, although you will have to make a small change to the ChartView.java file (when I last checked, this was on line 29).


---


<br />

## Step 4 ##

Put activity\_graph.xml into the /layout folder of your Android project. You could just make your own version of this XML document in the Android project, but I include this for your convenience.

If you chose **not** to heed my advice of putting the .java files in "com/willmanson/chartview", you will need to change the XML file. Namely, you will have to change the first line and the last line. It should be clear what you need to do.


---


<br />

## Step 5 ##

The GraphActivity.java file is only really present to demonstrate different ways of using ChartView.

If you're just looking to test the charting capabilities of ChartView, you're welcome to load up GraphActivity.java directly by making the necessary changes to the AndroidManifest file. Provided that you have followed the instructions up to now, it should run perfectly.

Otherwise you are free to delete this file. It is not integral to the running of ChartView.


---


<br />

## Step 6 ##

When you now need to use ChartView in your application, just import it in the usual way and it's good to go! Again, check out the GraphActivity.java file included if you want to see an example of ChartView being used.

The HelpPageExampleUsage page should also be of help when using ChartView.

Enjoy, have fun, and let me know if you make anything cool. I'll be interested to hear.