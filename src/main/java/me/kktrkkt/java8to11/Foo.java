package me.kktrkkt.java8to11;

import java.util.function.*;

public class Foo {

    // 함수형 인터페이스와 람다 표현식 소개
    static public void chapter1(){
        //익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };
        runSomething.doIt();
        //람다 표현식
        RunSomething lambdaRunSomething = () -> System.out.println("Hello");
        lambdaRunSomething.doIt();
        int baseNumber = 10;
        //외부 변수에 의존하는 함수형 인터페이스
        RunSomething notPureRunSomething = new RunSomething() {
            @Override
            public void doIt() {
                // baseNumber++; 외부에서 참조하는 변수는 불변이어야한다.
                System.out.println("Hello"+baseNumber);
            }
        };
        notPureRunSomething.doIt();
    }

    public static void main(String[] args) {
//        chapter1();
        Function<Integer, Integer> plus10 = i -> i+10;
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> multiply2 = i -> i*2;
        System.out.println(plus10.compose(multiply2).apply(2)); // multiply2 -> plus10 호출
        System.out.println(plus10.andThen(multiply2).apply(2)); // plus10 -> multiply2 호출

        BiFunction<Integer, Integer, Integer> plus = (a, b) -> a + b;
        Consumer<String> printName = name -> { System.out.println(name); };
        Supplier<Integer> get10 = () -> 10;
        Predicate<String> startsWithA = str -> str.startsWith("A");
        UnaryOperator<Integer> plus10UO = i -> i+10;
        BinaryOperator<Integer> plusBO = (a, b) -> a + b;

        System.out.println(plus.apply(10, 20));
        printName.accept("KKTRKKT");
        System.out.println(get10.get());
        System.out.println(startsWithA.test("Admin"));
        System.out.println(plus10UO.apply(1));
        System.out.println(plusBO.apply(10, 20));
    }
}
