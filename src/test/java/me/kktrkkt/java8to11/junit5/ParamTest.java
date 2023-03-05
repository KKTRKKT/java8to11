package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParamTest {

    @ParameterizedTest
    @ValueSource(strings = {"파란", "하늘", "꿈"})
    void parameterized_test(String value) {
        System.out.println(value);
    }

}