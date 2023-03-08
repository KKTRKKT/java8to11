package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionTest {

    @Test
    void assume_true() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    void assuming_that() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        assumingThat("LOCAL".equalsIgnoreCase(test_env), ()->{
            System.out.println("local");
            Study study = new Study(10);
            assertNotNull(study);
        });

        assumingThat("kktrkkt".equalsIgnoreCase(test_env), ()->{
            System.out.println("kktrkkt");
            Study study = new Study(10);
            assertNotNull(study);
        });
    }
}