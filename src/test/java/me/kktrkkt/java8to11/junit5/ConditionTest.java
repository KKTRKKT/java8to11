package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionTest {

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void enabled_on_os() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @DisabledOnOs({OS.MAC, OS.LINUX})
    void disabled_on_os() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void enabled_on_jre() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void enabled_if_environment_variable_local() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "kktrkkt")
    void enabled_if_environment_variable_kktrkkt() {
        Study study = new Study(10);
        assertNotNull(study);
    }
}