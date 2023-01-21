package me.kktrkkt.java8to11.intrefaceAlternation;

public interface Greeting {

    void printName();


    /**
     * @implSpec
     * 이 구현체는 getName()에서 넘겨받은 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        //getName이 null일 경우 런타임에러가 난다.
        System.out.println(getName().toUpperCase());
    }

    String getName();

//    String toString(); 선언 가능, Object의 구현체이기 때문에 추상메소드로 보진 않고 제약사항으로 본다.

//    default String toString(){} Object 메소드 오버라이드 불가
}
