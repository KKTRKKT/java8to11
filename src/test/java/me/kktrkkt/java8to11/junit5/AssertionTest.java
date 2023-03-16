package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.domain.StudyStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertionTest {

    @Test
    @DisplayName("새로운 스터디 생성")
    void create_new_study() {
        Study study = new Study(1);

        //실패한 모든 Assertions을 한번에 알 수 있다.
        assertAll(
                () -> assertNotNull(study),
                // lamda를 사용하면 항상 소모되던 메세지 비용을, 에러가 날때만 소모하게 된다.
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                    () -> "스터디를 처음 만들면 상태값이 DRAFT가 되어야 한다."),
                () -> assertTrue(study.getLimitCount() > 0, "스터디 참석 인원은 0보다 커야 한다."),
                () -> assertTimeout(Duration.ofMillis(100), ()->{
                    Thread.sleep(200);
                }),
                // timeout이 끝나면 즉시 종료된다. 실행 로직에 ThreadLocal 로직이 있을시 문제가 발생 할 수 있음
                () -> assertTimeoutPreemptively(Duration.ofMillis(100), ()->{
                    Thread.sleep(200);
                })
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(0));
        assertEquals("limit은 0보다 커야됩니다.", exception.getMessage());
    }
}