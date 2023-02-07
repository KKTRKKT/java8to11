package me.kktrkkt.java8to11.classloader;

public class App {

    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();
        // 애플리케이션 클래스 로더
        System.out.println(classLoader);
        // 플랫폼 클래스 로더
        System.out.println(classLoader.getParent());
        // 부트 스트랩 클래스 로더(Native로 구현되어 있어 null이 나온다)
        System.out.println(classLoader.getParent().getParent());
    }
}
