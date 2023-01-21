package me.kktrkkt.java8to11.intrefaceAlternation;

//Foo와 Greeting에서 printNameUpperCase 메소드가 충돌나므로 반드시 재정의 해야한다
public class DefaultGreeting implements Greeting, Foo {

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
