package me.kktrkkt.java8to11.processor;

public class App {

    public static void main(String[] args) {
        // @Magic이 pullOut이 선언된 인터페이스에 붙어있을때, pullOut을 구현하는 MagicMoja 클래스를 제공한다.
        Moja moja = new MagicMoja();
        System.out.println(moja.pullOut());
    }
}
