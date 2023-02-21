package me.kktrkkt.java8to11.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        aboutClass();
//        aboutFiledAndMethod();
//        aboutAnnotaion();

        System.out.println("--------------------------인스턴스 생성-------------------------------");
        // class의 newInstance는 Deprecated되었으므로, 생성자를 이용해 인스턴스를 생성한다.
        Constructor<Book> constructor = Book.class.getConstructor();
        Book book = constructor.newInstance();
        System.out.println(book);

    }

    private static void aboutAnnotaion() {
        System.out.println("--------------------------getAnnotations-------------------------------");
        // 기본적으로 annotation은 주석과 같은 취급을 받는다.
        // 따라서 바이트 코드 로딩 후에 메모리 영역에 남아있지 않는다.
        // 런타임에 애노테이션 정보를 읽어오기 위해서는 @Retention(RetentionPolicy.RUNTIME)을 추가해줘야한다.
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        System.out.println("--------------------------@Inherited-------------------------------");
        // 애노테이션은 상속이 가능하다.
        // 인터페이스는 상속 불가능.
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        System.out.println("--------------------------getDeclearedAnnotaion-------------------------------");
        // 상속받은 애노테이션을 제외하고 출력할 때 사용한다.
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);

        System.out.println("--------------------------필드의 모든 annotaion값 가져오기-------------------------------");

        // 모든 필드의 모든 annotaion 값을 출력하는 방법
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f->{
            Arrays.stream(f.getAnnotations()).forEach(System.out::println);
        });

        // 특정 애노테이션의 값만 출력하는 방법
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f->{
            Arrays.stream(f.getAnnotations()).forEach(a ->{
                if(a instanceof MyAnnotation){
                    MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.value());
                    System.out.println(myAnnotation.name());
                    System.out.println(myAnnotation.number());
                }
            });
        });
    }

    private static void aboutFiledAndMethod() {
        System.out.println("--------------------------getModifiers-------------------------------");
        // 변수의 접근제한자를 구분할 수 있다.
        Arrays.stream(Book.class.getDeclaredFields())
                .filter(f->Modifier.isPrivate(f.getModifiers()))
                .forEach(System.out::println);

        System.out.println("--------------------------getParameterTypes-------------------------------");
        // 매개변수를 가져올 수 있다.
        Arrays.stream(Book.class.getConstructors()).forEach(f->{
            Class<?>[] parameterTypes = f.getParameterTypes();
            System.out.println(f);
            Arrays.stream(parameterTypes).forEach(System.out::println);
        });

        System.out.println("--------------------------getReturnType-------------------------------");
        // 리턴 타입을 가져올 수 있다.
        Arrays.stream(Book.class.getDeclaredMethods()).forEach(f->{
            Class<?> returnType = f.getReturnType();
            System.out.println(f);
            System.out.println(returnType);
        });

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
