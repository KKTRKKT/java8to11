package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(FindSlowTestExtension.class)
class ExtendTest {

    @SlowTest
    void slow_test() throws InterruptedException {
        Thread.sleep(1005L);
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    void test() throws InterruptedException {
        Thread.sleep(1005L);
        Study study = new Study(10);
        assertNotNull(study);
    }
}