package me.kktrkkt.java8to11.junit5;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomTagTest {

    @FastTag
    void fast_tag() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @SlowTag
    void slow_tag() {
        Study study = new Study(10);
        assertNotNull(study);
    }
}