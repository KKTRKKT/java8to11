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
    }
}