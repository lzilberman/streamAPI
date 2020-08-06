package com.stream.examples;

public class Interval {
private int min;
private int max;
public Interval(int min, int max) {
	super();
	this.min = min;
	this.max = max;
}
public int getMin() {return min;}
public int getMax() {return max;}
@Override
public String toString() {return min + "-" + max;}

}
