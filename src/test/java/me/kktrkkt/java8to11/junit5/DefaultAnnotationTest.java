package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DefaultAnnotationTest {

    @Test
    @DisplayName("생성_1")
    void create1() {
        System.out.println("create1");
    }

    @Test
    @Disabled
    void create_2() {
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