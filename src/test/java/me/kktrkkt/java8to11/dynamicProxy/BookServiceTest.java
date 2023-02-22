package me.kktrkkt.java8to11.dynamicProxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new BookServiceImpl();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 특정 메소드만 적용하고 싶다면 조건문을 추가해야한다.
                    // rent 메소드만 앞뒤로 출력을 덧붙이도록 한다
                    if(!method.getName().equals("rent")) {
                        return method.invoke(bookService, args);
                    }
                    System.out.println("aaa");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("bbb");
                    return invoke;
                }
            });

    @Test
    public void rent() {
        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}