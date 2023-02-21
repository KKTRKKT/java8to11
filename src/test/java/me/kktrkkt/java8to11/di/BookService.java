package me.kktrkkt.java8to11.di;

public class BookService {

    @Inject
    BookRepository bookRepository;

    public void join(){
        System.out.println("Join");
        bookRepository.save();
    }
}
