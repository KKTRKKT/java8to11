package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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