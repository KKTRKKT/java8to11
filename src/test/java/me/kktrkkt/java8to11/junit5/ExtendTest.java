package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// 확장 모델을 선언적으로 추가하는 방식
//@ExtendWith(FindSlowTestExtension.class)
class ExtendTest {

    // 확장 모델을 프로그래밍적으로 추가하는 방식
    @RegisterExtension
    static FindSlowTestExtension extension = new FindSlowTestExtension(3000L);

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