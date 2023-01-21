package me.kktrkkt.java8to11;

public class InterfaceAlternation {

    public static void main(String[] args) {
        Greeting greeting = new DefaultGreeting("greeting");
        greeting.printName();
        greeting.printNameUpperCase();
    }
}
