package com.stream.examples;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApiTwo {

	public static void main(String[] args) {
//		displayOccurances();
		displayDigitsStatistics();
	}

	private static void displayOccurances() {
		String[] words = {"lmn","abc","ab","lmn","abc","ab","lmn"};
		
		// 1.Collector - grouping
		Map<Object, List<String>> mapString = 
				Arrays.stream(words).collect(Collectors.groupingBy(x->x));
		
		System.out.println(mapString);
		System.out.println();
		
		System.out.println(Arrays.stream(words)
				.collect(Collectors.groupingBy(x->x.length())));
		System.out.println();
		System.out.println(Arrays.stream(words)
				.filter(x->x.length()==3)
				.collect(Collectors.groupingBy(x->x.length())));
		System.out.println();
		System.out.println(Arrays.stream(words)
				.collect(Collectors.groupingBy(x->x, Collectors.counting())));
		System.out.println();
		
		Arrays.stream(words)
		.collect(Collectors.groupingBy(x->x, Collectors.counting()))
		.entrySet().stream()
		.sorted((x,y)->x.getValue()==y.getValue()?
				x.getKey().compareTo(y.getKey()):Long.compare(y.getValue() , x.getValue()) ).
		forEach(x->System.out.printf("%s->%d\n", x.getKey(), x.getValue()));
		
		System.out.println();
	}

	private static void displayDigitsStatistics() {
		new Random()
		.ints(1000000, 1, Integer.MAX_VALUE)
		.boxed()
		.map(x->x.toString())  
		.flatMapToInt(x->x.chars())
		.map(x->x-'0')
		.boxed()
		.collect(Collectors.groupingBy(x->x, Collectors.counting()))
		.entrySet()
		.stream()
		.sorted( (x,y)->Long.compare(y.getValue() , x.getValue()) )
		.forEach(x->System.out.printf("%d->%d\n", x.getKey(), x.getValue() ));		
	}
	
}
