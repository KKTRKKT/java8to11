package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

class RepeatTest {

    @RepeatedTest(value = 10, name = "{displayName}({currentRepetition} / {totalRepetitions})")
    void repeated_test(RepetitionInfo info) {
        System.out.println(info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }
}