package me.kktrkkt.java8to11.junit5;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomTagTest {

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