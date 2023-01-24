package me.kktrkkt.java8to11.api.defaultMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "strawberry");

        fruits.forEach(System.out::println);
        
        Spliterator<String> spliterator = fruits.spliterator();
        Spliterator<String> split = spliterator.trySplit();
        while(split.tryAdvance(System.out::println));
        while(spliterator.tryAdvance(System.out::println));
    }
}
