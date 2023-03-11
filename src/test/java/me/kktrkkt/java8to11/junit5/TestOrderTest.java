package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.*;

// 테스트 인스턴스 생성 단위를 클래스로 변경한다. 기본은 메소드
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @Order에 적힌 숫자가 낮을수록 먼저 테스트를 진행한다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestOrderTest {
    int value = 0;

    @Order(3)
    @Test
    void third_test() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(1)
    @Test
    void first_test() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(2)
    @Test
    void second_test() {
        System.out.println(this);
        System.out.println(value++);
    }

}