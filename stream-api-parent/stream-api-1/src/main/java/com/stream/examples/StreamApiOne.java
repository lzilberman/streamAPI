package com.stream.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiOne {
	/* USEFUL LINKS
	 * ============
	 * https://howtodoinjava.com/java8/java-streams-by-examples/
	 * https://habr.com/ru/post/437038/
	 * 
	 * 
	 */
	private static List<String> memberNames = new ArrayList<>(Arrays.asList("Amitabh", "Shekhar", "Aman", "Rahul", 
            "Shahrukh", "Salman", "Yana", "Lokesh"));


	public static void main(String[] args) {
//		part_2_3();
//		part_41();
		part_42();
//		part_56();
	}

	private static void part_2_3() {
		/// 2. Different ways to create streams 
		/// https://howtodoinjava.com/java8/java-streams-by-examples/
						
		///		  ##Stream.of##
				        Stream<Integer> streamInt = Stream.of(1,2,3,4,5,6,7,8,9);
				        streamInt.forEach(p -> System.out.println(p));
						System.out.println();
						
				        Stream<Integer> streamArr = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
				        streamArr.forEach(p -> System.out.println(p));
				        System.out.println();
				        
		///       ##collection.stream()##
					        List<Integer> list = new ArrayList<Integer>();			        
					        for(int i = 1; i< 10; i++){
					            list.add(i);
					        }			
					        Stream<Integer> streamList = list.stream();
					        streamList.forEach(p -> System.out.println(p));
					        System.out.println();
		///		  ##Stream.generate##
//				        Stream<Date> streamDate = Stream.generate(() -> { return new Date(); });
//				        streamDate.forEach(p -> System.out.println(p));
//				        System.out.println();
					        
		///          ##String chars or String tokens##
				        IntStream streamChars = "12345_abcdefg".chars();
				        streamChars.forEach(p -> System.out.println(p-'0'));
				        System.out.println();
				        
		///============ 3. Convert streams to collections 
		// 3.1. Convert Stream to List – Stream.collect( Collectors.toList() )
				        List<Integer> list2 = new ArrayList<Integer>();
				        for(int i = 1; i< 10; i++){
				            list2.add(i);
				        }
				        
				        Stream<Integer> stream = list2.stream();
				        
				        List<Integer> evenNumbersList = stream.filter(i -> i%2 == 0).collect(Collectors.toList());
				        System.out.print(evenNumbersList);
				        System.out.println();
				        
		// 3.2. Convert Stream to array – Stream.toArray( EntryType[]::new )	
				        Stream<Integer> streamEven = list2.stream();
						Integer[] evenNumbersArr = streamEven.filter(i -> i%2 == 0).toArray(Integer[]::new);
				// /       System.out.print(evenNumbersArr);
				        for(int i = 0; i< evenNumbersArr.length; i++){
				        	System.out.print(evenNumbersArr[i]);
				        }
			}

			private static void part_41() {
		/// 4. Core stream operations  4.1. Intermediate operations  4.1.1. Stream.filter()
				
				memberNames.stream().filter((s) -> s.startsWith("A"))
		        .forEach(System.out::println);	
				System.out.println();
				
		/* 4.1.2. Stream.map()
		=====================
		The intermediate operation map converts each element into another object via the given function. 
		The following example converts each string into an upper-cased string. But you can also use map() 
		to transform each object into another type.		
		 */
				memberNames.stream().filter((s) -> s.startsWith("A"))
		        .map(String::toUpperCase)
		        .forEach(System.out::println);
				System.out.println();
		/* 4.1.2. Stream.sorted()	
		========================	
		Sorted is an intermediate operation that returns a sorted view of the stream. 
		The elements are sorted in natural order unless you pass a custom Comparator.		
		 */
//				memberNames.stream().sorted()
				memberNames.stream().sorted((x,y)->y.compareTo(x))
		        .map(String::toUpperCase)
		        .forEach(System.out::println);	
				
				System.out.println();		
		/* 18_05_21_StreamsPractice  private static void displaySportlotoNumbers() */
				
				new Random().ints(1, 48).distinct().limit(7)
				.forEach(x->System.out.printf("%d ", x));	
				
				System.out.println();
		/* 18_05_21_StreamsPractice  private static void shuffleArray(int [] arr)  */
				int[] arr = {1,2,3,4,5,6,7,8,9,100};
				
				new Random().ints(0, arr.length).distinct().limit(arr.length)
				.forEach(i->System.out.print(arr[i] + " "));		
				
				System.out.println();		
		/* 18_05_21_StreamsPractice  private static void displayDigitsSum(String[] strNumbers) */
				String[] strNumbers = {"123", "24", "459"};
				System.out.println(Arrays.stream(strNumbers)
				.flatMapToInt(x->x.chars())
				.map(x->x-'0').sum());
		/*
		 <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
		               =======                                                   =======
		Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream 
		produced by applying the provided mapping function to each element. Each mapped stream is closed after its contents have been placed into this stream.
		API Note:
		The flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream, 
		and then flattening the resulting elements into a new stream.	

		IntStream flatMapToInt(Function<? super T,? extends IntStream> mapper)
		          ============                                         ======
		Returns an IntStream consisting of the results of replacing each element of this stream with the contents of a mapped stream 
		produced by applying the provided mapping function to each element. 
		*/
			}

			private static void part_42() {
		/*  4.2. Terminal Operations 4.2.1. Stream.forEach()
		======================================================
		This method helps in iterating over all elements of a stream and perform some 
		operation on each of them. The operation is passed as the lambda expression parameter.	
			memberNames.forEach(System.out::println);
		 */
		/*  4.2.2. Stream.collect()
		===========================
		collect() method used to receive elements from a steam and store them in a collection 
		and mentioned in parameter function. 
		 */
				List<String> memNamesInUppercase = memberNames.stream().sorted()
		                .map(String::toUpperCase)
		                .collect(Collectors.toList());

		        System.out.print(memNamesInUppercase);	
		        System.out.println();
		        
		/* 18_05_21_StreamsPractice  private static void printEvenOdd() */
				new Random()
				.ints(1000000, 0, Integer.MAX_VALUE)
				.boxed()
				.collect(Collectors.groupingBy(x->x%2==0? "even: ": "odd: ", Collectors.counting()))
				.forEach((k,v)->System.out.printf("%s %d\n", k, v));
				
				System.out.println();
		/*
		public static <T,K,A,D> Collector<T,?,Map<K,D>> groupingBy(Function<? super T,? extends K> classifier,
		                                                =========  Collector<? super T,A,D> downstream)
		                                                           
		Returns a Collector implementing a cascaded "group by" operation on:
		 
		input elements of type T, 
		grouping elements according to a classification function, 
		and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.

		The classification function maps elements to some key type K. 
		The downstream collector operates on elements of type T and produces a result of type D. 
		The resulting collector produces a Map<K, D>.

		There are no guarantees on the type, mutability, serializability, or thread-safety of the Map returned.

		For example, to compute the set of last names of people in each city:

		     Map<City, Set<String>> namesByCity
		         = people.stream().collect(groupingBy(Person::getCity,
		                                              mapping(Person::getLastName, toSet())));
		 
		Implementation Note:
		The returned Collector is not concurrent. For parallel stream pipelines, the combiner function operates by merging the keys from one map into another, which can be an expensive operation. If preservation of the order in which elements are presented to the downstream collector is not required, using groupingByConcurrent(Function, Collector) may offer better parallel performance.
		Type Parameters:
		T - the type of the input elements
		K - the type of the keys
		A - the intermediate accumulation type of the downstream collector
		D - the result type of the downstream reduction
		Parameters:
		classifier - a classifier function mapping input elements to keys
		downstream - a Collector implementing the downstream reduction
		Returns:
		a Collector implementing the cascaded group-by operation 		
		*/
//				https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#mapping-java.util.function.Function-java.util.stream.Collector-
//				https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toSet--		
//				https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#counting--
				
		/*  4.2.3. Stream.match()
		=========================
		Various matching operations can be used to check whether a certain predicate matches the stream. 
		All of those operations are terminal and return a boolean result.	      
		 */
		        boolean matchedResult = memberNames.stream()
		                .anyMatch((s) -> s.startsWith("A"));
				System.out.println(matchedResult);
				System.out.println();
				
				matchedResult = memberNames.stream()
				                .allMatch((s) -> s.startsWith("A"));		
				System.out.println(matchedResult);
				System.out.println();
				
				matchedResult = memberNames.stream()
				                .noneMatch((s) -> s.startsWith("A"));
				
				System.out.println(matchedResult);
				System.out.println();
		/*  4.2.4. Stream.count()
		==========================
		Count is a terminal operation returning the number of elements in the stream as a long value.		
		 */
				long totalMatched = memberNames.stream()
		                .filter((s) -> s.startsWith("A"))
		                .count();

				System.out.println(totalMatched);	
		/*  4.2.5. Stream.reduce()
		==========================
		This terminal operation performs a reduction on the elements of the stream with the given function. 
		The result is an Optional holding the reduced value.
		 */
				Optional<String> reduced = memberNames.stream()
		                .reduce((s1,s2) -> s1 + "#" + s2);

				reduced.ifPresent(System.out::println);		
						
			}

			private static void part_56() {	
		/* 5. Stream short-circuit operations 
		======================================
		Though stream operations are performed on all elements inside a collection satisfying a predicate, 
		It is often desired to break the operation whenever a matching element is encountered during iteration. 
		In external iteration, you will do with the if-else block. In internal iteration, there are certain methods you can use for this purpose. 
		Let’s see an example of two such methods:
		 */
		/* 5.1. Stream.anyMatch() 5.2. Stream.findFirst()
		 =========================
		1.This will return true once a condition passed as predicate satisfy. 2.It will return the first element from the stream 
		It will not process any more elements.		
		 */
				boolean matched = memberNames.stream()
		                .anyMatch((s) -> s.startsWith("A"));

		        System.out.println(matched);
		        
		        String firstMatchedName = memberNames.stream()
		                .filter((s) -> s.startsWith("W"))
		                .findFirst().orElse("Figu Vam");
//		                .findFirst().get();
		 
		        System.out.println(firstMatchedName);  
		        
		/* 6. Parallelism in Java Steam
		================================
		With the Fork/Join framework added in Java SE 7, we have efficient machinery for implementing parallel operations in our applications. 
		But implementing this framework is itself a complex task, and if not done right; it is a source of complex multi-threading bugs having 
		the potential to crash the application. With the introduction of internal iteration, we got the possibility of operations to be done in parallel.

		To enable parallelism, all you have to do is to create a parallel stream, instead of a sequential stream. 
		And to surprise you, this is really very easy. In any of the above-listed stream examples, anytime you want to particular job using multiple threads 
		in parallel cores, all you have to call method parallelStream() method instead of stream() method.        
		 */
		        List<Integer> list = new ArrayList<Integer>();
		        for(int i = 1; i< 10; i++){
		            list.add(i);
		        }
		        //Here creating a parallel stream
		        Stream<Integer> stream = list.parallelStream();  
		        Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
		        System.out.print(Arrays.toString(evenNumbersArr));        
			}
	
	
}
