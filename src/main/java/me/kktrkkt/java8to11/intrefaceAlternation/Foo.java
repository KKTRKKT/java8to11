package me.kktrkkt.java8to11.intrefaceAlternation;

public interface Foo {
    default void printNameUpperCase() {
        System.out.println("FOO");
    }
}
