package me.kktrkkt.java8to11.reflection;

import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 로딩이 끝나면 클래스 타입의 인스턴스를 만들어서 힙에 저장됨
        System.out.println("--------------------------class 가져오기-------------------------------");
        // 타입에서 가져오는 방법
        Class<Book> bookClass = Book.class;

        // 인스턴스에서 가져오는 방법
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // FQCN(Full Qualified Class Name)으로 찾는 방법
        Class<?> forName = Class.forName("me.kktrkkt.java8to11.reflection.Book");

    }
}
