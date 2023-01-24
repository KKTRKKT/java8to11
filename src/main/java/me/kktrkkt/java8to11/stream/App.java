package me.kktrkkt.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("watermelon");
        fruits.add("blueberry");

        //원본 소스를 변경하지 않으며, 데이터는 한번만 처리된다.
        fruits.stream().map(String::toUpperCase).forEach(System.out::println);
        fruits.forEach(System.out::println);

        // 무한일 수 있다.
//        IntStream.iterate(1, i->i+1).forEach(System.out::println);

        //short circuit 메소드를 통해 제한할 수 있다.(lazy 하다)
        int first = IntStream.iterate(1, i -> i + 1).findFirst().getAsInt();
        System.out.println(first);

        //병렬처리가 쉽다.
        fruits.parallelStream().
                map(s -> {
                    System.out.println("current Thread:" + Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(System.out::println);
    }
}
