package me.kktrkkt.java8to11;

public class Foo {

    public static void main(String[] args) {
        //익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };
        runSomething.doIt();
    }
}
