
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class CollectionOperation {

    public static void main(String[] args) {
        List<Integer> listOfIntegers
                = new ArrayList<>(Arrays.asList(4, 7, 1, 3, 8, 9, 2, 6, 10));
//        Comparator<Integer> comparator = Integer::compare;
//        Collections.sort(listOfIntegers, comparator);
//        listOfIntegers.stream().filter(v -> v > 5).forEach(v -> {
//            System.out.println(v);
//        });
//        Collector<Integer, ?, IntSummaryStatistics> collector
//                = Collectors.summarizingInt(Integer::intValue);
//        IntSummaryStatistics summaryStatistics = listOfIntegers.stream().collect(collector);
//        System.out.println("Total = " + summaryStatistics.getSum());
//        int sum = listOfIntegers.stream().reduce(0, (x, y) -> x + y);
//        System.out.println("Total = " + sum);
////        int sum = listOfIntegers.stream().reduce(0, Integer::sum);
////        System.out.println("Total = "+sum);
//        System.out.println("Average = " + summaryStatistics.getAverage());
//        Consumer<Integer> myConsumer = n -> {
//            System.out.println("User input value = " + n);
//            if (n < 5) {
//                System.out.println("Invalid value!");
//                return;
//            }
//            listOfIntegers.add(n);
//            System.out.println("Values: " + String.valueOf(n));
//            listOfIntegers.forEach(x
//                    -> System.out.print(x + "-"));
//        };
//        myConsumer.accept(1);
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Please input a number: ");
//            int value = sc.nextInt();
//            myConsumer.accept(value);
//            System.out.println("");
//        }
        Predicate<Integer> tester = v -> v>5;
        Consumer<Integer> myConsumer = n -> listOfIntegers.add(n);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please input a number: ");
            int value = sc.nextInt();
            if(value<0) break;
            if(tester.test(value)) myConsumer.accept(value);
        }
        listOfIntegers.forEach(x -> System.out.print(x+"-"));
    }

}
