package me.kktrkkt.java8to11.intrefaceAlternation;

public class DefaultGreeting implements Greeting{

    String name;

    public DefaultGreeting(String name) {
        this.name = name;
    }

    // default 메소드 재정의 가능
    @Override
    public void printNameUpperCase() {
        Greeting.super.printNameUpperCase();
    }

    @Override
    public void printName() {
        System.out.println(name);
    }

    @Override
    public String getName() {
        return name;
    }

}
