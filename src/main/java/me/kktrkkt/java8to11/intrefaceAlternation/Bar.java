package me.kktrkkt.java8to11.intrefaceAlternation;

public interface Bar extends Greeting{
    //구현체 메소드를 다시 추상화 메소드로 전환
    void printNameUpperCase();
}
