package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 테스트명에 공통적으로 적용할 규칙을 설정합니다
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DefaultAnnotationTest {

    // 테스트 메소드를 선언합니다
    @Test
    // DisplayNameGeneration보다 우선적으로 테스트명을 설정합니다
    @DisplayName("생성_1")
    void create1() {
        System.out.println("create1");
    }

    @Test
    // 테스트에서 제외합니다
    @Disabled
    void create_2() {
        System.out.println("create2");
    }

    // 모든 테스트가 시작되기 전에 한번만 실행됩니다
    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    // 모든 테스트가 끝난 후 한번만 실행됩니다
    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }

    // 모든 각각의 테스트의 시작 전에 실행됩니다
    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    // 모든 각각의 테스트가 끝난 후에 실행됩니다.
    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }
}