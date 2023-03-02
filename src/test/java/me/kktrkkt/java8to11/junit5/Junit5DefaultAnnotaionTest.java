package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Junit5DefaultAnnotaionTest {

    @Test
    void create1() {
        System.out.println("create1");
    }

    @Test
    @Disabled
    void create2() {
        System.out.println("create2");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }
}