package me.kktrkkt.java8to11.api.defaultMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("strawberry");

        fruits.forEach(System.out::println);

        System.out.println("-------------------------------");

        Spliterator<String> spliterator = fruits.spliterator();
        Spliterator<String> split = spliterator.trySplit();
        while(split.tryAdvance(System.out::println));
        while(spliterator.tryAdvance(System.out::println));

        System.out.println("-------------------------------");

        long k = fruits.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(k);

        System.out.println("-------------------------------");

        fruits.removeIf(s -> s.startsWith("a"));
        fruits.forEach(System.out::println);
    }
}
