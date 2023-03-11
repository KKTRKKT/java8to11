package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionTest {

    @Test
    // 조건에 맞는 OS만 테스트를 실행한다.
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void enabled_on_os() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    // 조건에 맞는 OS는 테스트에서 제외한다.
    @DisabledOnOs({OS.MAC, OS.LINUX})
    void disabled_on_os() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    // 다음 JRE 버전만 실행한다.
    @EnabledOnJre(JRE.JAVA_11)
    void enabled_on_jre() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    // 환경변수가 matches와 일치하면 테스트를 실행한다.
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