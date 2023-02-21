package me.kktrkkt.java8to11.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContainerServiceTest {

    @Test
    public void getObject_BookRepository(){
        BookRepository bookRepository;
        bookRepository = new ContainerService().getObject(BookRepository.class);
        assertEquals(BookRepository.class, bookRepository.getClass());
    }

    @Test
    public void getObject_BookService(){
        BookService bookService;
        bookService = new ContainerService().getObject(BookService.class);
        assertEquals(BookService.class, bookService.getClass());
        assertNotNull(bookService.bookRepository);
        bookService.join();
    }
}