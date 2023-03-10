package me.kktrkkt.java8to11.reflection;

// 애노테이션 value가 있으면, value에  자동 매핑된다
@MyAnnotation("Book")
public class Book {

    @MyAnnotation("String")
    private static String b = "Book";

    private static final String c = "Book";

    private String a;

    public String d;

    protected String e = "e";

//    @MyAnnotation
    public Book() {

    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }

    public static String sum(String left, String right){
        return left + right;
    }
}
