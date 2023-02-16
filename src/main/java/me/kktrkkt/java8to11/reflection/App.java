package me.kktrkkt.java8to11.reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
//        aboutClass();
        System.out.println("--------------------------getModifiers-------------------------------");
        // 변수의 접근제한자를 구분할 수 있다.
        Arrays.stream(Book.class.getDeclaredFields())
                .filter(f->Modifier.isPrivate(f.getModifiers()))
                .forEach(System.out::println);

    }

    private static void aboutClass() throws ClassNotFoundException {
        // 클래스 로딩이 끝나면 클래스 타입의 인스턴스를 만들어서 힙에 저장됨
        System.out.println("--------------------------class 가져오기-------------------------------");
        // 타입에서 가져오는 방법
        Class<Book> bookClass = Book.class;

        // 인스턴스에서 가져오는 방법
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // FQCN(Full Qualified Class Name)으로 찾는 방법
        Class<?> forName = Class.forName("me.kktrkkt.java8to11.reflection.Book");

        System.out.println("--------------------------getFields-------------------------------");
        // public한 변수들만 가져온다.
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        System.out.println("--------------------------getDeclaredFields-------------------------------");
        // 접근지시자 제한없이 모든 변수들을 가져온다.
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        System.out.println("--------------------------필드 값 가져오기-------------------------------");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f->{
            try {
                // 모든 변수의 접근을 허용해준다. private한 변수를 접근할 때 꼭 true로 설정해야한다.
                f.setAccessible(true);
                System.out.printf("%s, %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println("--------------------------getMethod-------------------------------");
        // 상위에 메소드들까지 모두 가져온다.
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println("--------------------------getConstructors-------------------------------");
        // 생성자를 가져온다.
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

        System.out.println("--------------------------getSuperclass-------------------------------");
        // 상속받은 수퍼 클래스를 가져온다.
        Class<? super MyBook> superclass = MyBook.class.getSuperclass();
        System.out.println(superclass);

        System.out.println("--------------------------getInterfaces-------------------------------");
        // 구현한 인터페이스를 모두 가져온다.
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }
}
