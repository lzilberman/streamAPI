package com.stream.examples;
import java.util.ArrayList;

public class Employee {
	
public Employee(String name, String company, int salary) {
		super();
		this.name = name;
		this.company = company;
		this.salary = salary;
	}

private String name;
private String company;
private int salary;

private static ArrayList<Interval> salaryIntervals = new ArrayList<>();
static {
	salaryIntervals.add(new Interval(5000, 10000));
	salaryIntervals.add(new Interval(10000, 15000));
	salaryIntervals.add(new Interval(15000, 20000));
	salaryIntervals.add(new Interval(20000, 25000));
	salaryIntervals.add(new Interval(25000, 30000));	
}
public String getSalaryInterval(){
	
	for(Interval x: salaryIntervals) {
		if(x.getMin() <= this.salary && x.getMax()> this.salary) return x.toString();
	}
	return "Salary out of any defined intrerval";
}

public String getCompany() {return company;}
public int getSalary() {return salary;}
@Override
public String toString() {
	return "Employee [" + name + ", " + company + ", " + salary + "]";
}

}
