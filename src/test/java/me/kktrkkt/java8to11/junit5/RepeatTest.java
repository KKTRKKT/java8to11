package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

class RepeatTest {

    // 반복형 테스트, value에 몇번 반복할지, name에 테스트명을 설정할 수 있다.
    @RepeatedTest(value = 10, name = "{displayName}({currentRepetition} / {totalRepetitions})")
    void repeated_test(RepetitionInfo info) {
        System.out.println(info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }
}