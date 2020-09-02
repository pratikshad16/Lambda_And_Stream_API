package com.bridgelabz.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumberPlayList {
    public static  void main(String[] args) {
        // creating sample collection
        List<Integer> myNumberList = new ArrayList<Integer>();
        for(int i=0; i<5; i++) myNumberList.add(i);

        //Method1: traversing using iterator
        Iterator<Integer> it = myNumberList.iterator();
        while (it.hasNext()){
            Integer i = it.next();
            System.out.println("Mth1: Iterator Value::"+i);
        }

        //Method2: Traversing with Explicit Consumer interface implementation
        class MyConsumer implements Consumer<Integer> {
            public void accept(Integer t) {
                System.out.println("Mth2: Consumer impl Value::" + t);
            }
        }
        MyConsumer action = new MyConsumer();
        myNumberList.forEach(action);

        //Method3: Traversing with Anonymous Consumer interface implementation
        myNumberList.forEach(new Consumer<Integer>() {
            public void accept(Integer t) {
                System.out.println("Mth3: forEach anonymous class value::"+t);
            }
        });

        //Method4: Explicit Lambda Function
        Consumer<Integer> myListAction = n-> {
            System.out.println("Mth4: forEach Lambda impl value::"+ n);
        };
        myNumberList.forEach(myListAction);

        //Method5: Implicit lambda Function
        myNumberList.forEach(n -> {
            System.out.println("Mth5: forEach Lambda impl value::"+ n);
        });

        //Method6: Implicit Lambda Function to print double value
        Function<Integer,Double> toDoubleFunction = Integer::doubleValue;
        myNumberList.forEach(n -> {
            System.out.println("Mth6: forEach lambda double value::"+toDoubleFunction.apply(n));
        });

        //Method7: Implicit Lambda Function to check even
        Predicate<Integer> isEvenFunction = n -> n > 0 && n%2 == 0;
        myNumberList.forEach(n -> {
            System.out.println("Mth7: forEach value of: "+n+ " Check for Even: "+isEvenFunction.test(n));
        });

        //Method8: Processing the stream
        myNumberList.stream().forEach(n -> {
            System.out.println("Mth8: Stream for each value::"+n);
        });

        //Method9: Process the stream,Apply Operations on the stream and then store the result
        List<Double> doubleList = myNumberList.stream()
                                  .filter(isEvenFunction)
                                  .map(toDoubleFunction)
                                  .collect(Collectors.toList());
        System.out.println("Mth9: Printing Double List: "+doubleList);

        //Method 10: Listing the first Even
        Integer first = myNumberList.stream()
                        .filter(isEvenFunction)
                        .peek(n -> System.out.println("Peak Even Number: "+n))
                        .findFirst()
                        .orElse(null);
        System.out.println("Mth10: First Even: "+first);

        //Method 11:  get minimum even no
        Integer minimum = myNumberList.stream()
                      .filter(isEvenFunction)
                      .min((n1,n2)->n1-n2)
                      .orElse(null);
        System.out.println("Mth11: Minimum even number is "+minimum);

        //Method 12:   get maximum even no
        Integer maximum = myNumberList.stream()
                         .filter(isEvenFunction)
                         .max((n1,n2)->n1-n2)
                         .orElse(null);
        System.out.println("Mth12: Maximum even number is "+maximum);

        //Method 13 : find the average of all numbers
        Integer sum = myNumberList.stream()
                      .reduce(0, Integer::sum);
        long count =myNumberList.stream().count();
        System.out.println("Mth13: Average of all numbers is "+(sum/count));

        //Method 14: Checking all even,single even, or none are divisible by 6
        boolean allEven = myNumberList.stream().allMatch(isEvenFunction);
        boolean oneEven = myNumberList.stream().anyMatch(isEvenFunction);
        boolean noneMultOf6 = myNumberList.stream().anyMatch(integer -> integer !=0 && integer%6 == 0);
        System.out.println("allEven->"+allEven+"   oneEven->"+oneEven+"   noneMultOf6->"+noneMultOf6);

    }
}
