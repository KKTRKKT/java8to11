package me.kktrkkt.java8to11.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
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

    @Test
    public void rent_cglib() {
        BookService cglibBookService = (BookService) Enhancer.create(BookServiceImpl.class, new MethodInterceptor() {
            final BookServiceImpl bookService = new BookServiceImpl();

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if(!method.getName().equals("rent")) {
                    return method.invoke(bookService, args);
                }
                System.out.println("aaa");
                Object invoke = method.invoke(bookService, args);
                System.out.println("bbb");
                return invoke;
            }
        });

        Book book = new Book();
        book.setTitle("spring");
        cglibBookService.rent(book);
        cglibBookService.returnBook(book);
    }
}