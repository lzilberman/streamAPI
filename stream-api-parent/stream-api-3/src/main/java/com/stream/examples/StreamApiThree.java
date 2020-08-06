package com.stream.examples;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StreamApiThree {

	private static final int N_EMPLOYEES = 1000;
	private static final int MIN_SALARY = 5000;
	private static final int MAX_SALARY = 30000;
	private static final int N_NAMES = 100;
	private static final int N_COMPANIES = 10;
	
	public static void main(String[] args) {
		List<Employee> employees = getRandomEmployees(N_EMPLOYEES, MIN_SALARY, 
				MAX_SALARY, N_NAMES, N_COMPANIES);

//		employees.forEach((e)->System.out.printf("%s  %s\n",e.toString(), e.getSalaryInterval()));
//		System.out.println("\naverage salary: "+ getAvgSalary(employees)+"\n");
		
		displayCompaniesAvgSalary(employees);
		System.out.println();
		displaySalaryIntervalsOccurrences(employees); 		
		System.out.println();
		displayFilteredSalaryIntervalOccurrences(employees); 
	}

	private static void displaySalaryIntervalsOccurrences(List<Employee> employees) 
	{
		employees.stream()
		.collect(Collectors.groupingBy(Employee::getSalaryInterval, Collectors.counting()))
		.entrySet().stream()
		.sorted(StreamApiThree::compareIntervalEntryByKey)
		.forEach(x->System.out.printf("%s->%d\n", x.getKey(), x.getValue() ));
		
	}
	
	private static void displayFilteredSalaryIntervalOccurrences(List<Employee> employees) 
	{
		employees.stream()
		.filter(e->e.getSalaryInterval().compareToIgnoreCase("5000-10000") == 0)
		.collect(Collectors.groupingBy(Employee::getSalaryInterval, Collectors.counting()))
		.entrySet().stream()	
		.forEach(x->System.out.printf("%s->%d\n", x.getKey(), x.getValue() ));
		
	}	
	private static int compareIntervalEntryByKey(Entry<String, Long>x, Entry<String, Long>y) {
		int ix = Integer.parseInt(x.getKey().substring(0, x.getKey().indexOf('-')));
		int iy = Integer.parseInt(y.getKey().substring(0, y.getKey().indexOf('-')));
		return ix-iy;
	}

	private static void displayCompaniesAvgSalary(List<Employee> employees) {
		 /* <company name> <average salary for the company>
		 *  company1 20000.57  
		 */
//		DecimalFormat df = new DecimalFormat("0.00");      
		
		employees.stream()
		.collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingInt(Employee::getSalary)))
		.entrySet().forEach(x->System.out.printf("%s %.2f\n", x.getKey(), x.getValue() ));
//		.entrySet().forEach(x->System.out.printf("%s %s\n", x.getKey(), df.format(x.getValue()) ));
		
	}

//	private static String getAvgSalary(List<Employee> employees) {
//
////		return employees.stream().collect(Collectors.averagingInt
////				(x->x.getSalary())).toString();
//		return employees.stream().mapToInt(Employee::getSalary)
//				.average().toString(); 
//	}

	private static List<Employee> getRandomEmployees(int nEmployees, int minSalary, int maxSalary, 
			int nNames, int nCompanies) {
		List<Employee> employees = new ArrayList<>();
		
		for(int i=0; i<nEmployees; i++ ) {
			employees.add(getRandomEmployee(minSalary, maxSalary, nNames, nCompanies));
		}
		return employees;
	}

	private static Employee getRandomEmployee(int minSalary, int maxSalary, int nNames, int nCompanies) {
		String name = "name" + getRandomNumber(1, nNames);
		String company = "company"+ getRandomNumber(1, nCompanies);
		int salary = getRandomNumber(minSalary, maxSalary);
		
		return new Employee(name, company, salary);
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int)(min + Math.random()*(max-min+1));
	}
	
}
