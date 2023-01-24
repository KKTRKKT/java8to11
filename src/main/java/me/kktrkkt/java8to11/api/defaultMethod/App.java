package me.kktrkkt.java8to11.api.defaultMethod;

import java.util.*;

public class App {

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("apple", 2023));
        fruits.add(new Fruit("apple", 2022));
        fruits.add(new Fruit("banana", 2023));
        fruits.add(new Fruit("banana", 2022));
        fruits.add(new Fruit("strawberry", 2023));
        fruits.add(new Fruit("strawberry", 2022));

        System.out.println("--------------forEach-----------------");

        fruits.forEach(System.out::println);

        System.out.println("--------------spliterator-----------------");

        Spliterator<Fruit> spliterator = fruits.spliterator();
        Spliterator<Fruit> split = spliterator.trySplit();
        while(split.tryAdvance(System.out::println));
        while(spliterator.tryAdvance(System.out::println));

        System.out.println("--------------stream-----------------");

        long k = fruits.stream()
                .map(Fruit::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(k);

        System.out.println("--------------removeIf-----------------");

//        fruits.removeIf(f -> f.getName().startsWith("a"));
        fruits.forEach(System.out::println);

        System.out.println("--------------reveresed-----------------");

        fruits.sort(Comparator.comparing(Fruit::getName).reversed());
        fruits.forEach(System.out::println);

        System.out.println("--------------thenComparing-----------------");

        fruits.sort(Comparator.comparing(Fruit::getName).thenComparing(Fruit::getYear));
        fruits.forEach(System.out::println);

        System.out.println("--------------reverseOrder-----------------");

        fruits.sort(Comparator.reverseOrder());
        fruits.forEach(System.out::println);

        System.out.println("--------------naturalOrder-----------------");

        fruits.sort(Comparator.naturalOrder());
        fruits.forEach(System.out::println);

        System.out.println("--------------nullsFirst-----------------");

        fruits.add(null);
        fruits.sort(Comparator.nullsFirst(Fruit::compareTo));
        fruits.forEach(System.out::println);

    }
}
