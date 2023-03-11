package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomTagTest {

    // 커스텀 태그로 여러 애노테이션을 조합할 수 있다.
    @FastTest
    void fast_tag() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @SlowTest
    void slow_tag() {
        Study study = new Study(10);
        assertNotNull(study);
    }
}