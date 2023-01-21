package me.kktrkkt.java8to11.intrefaceAlternation;

public class InterfaceAlternation {

    public static void main(String[] args) {
        Greeting greeting = new DefaultGreeting("greeting");
        greeting.printName();
        greeting.printNameUpperCase();

        Greeting.printClass();
    }
}
