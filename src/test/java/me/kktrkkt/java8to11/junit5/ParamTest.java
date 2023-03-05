package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ParamTest {

    @ParameterizedTest(name = "[{index}] = {arguments} {displayName}")
    @ValueSource(strings = {"파란", "하늘", "꿈"})
    void parameterized_test(String value) {
        System.out.println(value);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void parameterized_test(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study);
    }

    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(targetType, Study.class, "Can only convert Study");
            return new Study((Integer) source);
        }
    }
}