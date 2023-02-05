package me.kktrkkt.java8to11.annotaion.chicken;

import java.util.Arrays;
import java.util.stream.Stream;

@Chicken("양념")
@Chicken("마늘 간장")
public class App {
    public static void main(String[] args) {
        // 중복 애노테이션의 값을 가져온다.
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
        Stream.of(chickens)
                .map(Chicken::value)
                .forEach(System.out::println);

        // 컨테이너 타입으로 가져오는 방법
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value())
                .map(Chicken::value)
                .forEach(System.out::println);
    }
}
