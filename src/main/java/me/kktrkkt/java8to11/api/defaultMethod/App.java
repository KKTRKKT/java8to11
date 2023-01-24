package me.kktrkkt.java8to11.api.defaultMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "strawberry");

        fruits.forEach(System.out::println);
    }
}
