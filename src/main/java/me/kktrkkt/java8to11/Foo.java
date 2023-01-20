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
}
