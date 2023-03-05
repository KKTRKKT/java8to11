package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
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
    void simple_argument_converter_test(@ConvertWith(StudyArgumentConverter.class) Study study) {
        System.out.println(study);
    }

    @ParameterizedTest
    @CsvSource(value={"1, '스프링 스터디'", "2, '자바 스터디'"})
    void parameterized_test(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyArgumentConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(targetType, Study.class, "Can only convert Study");
            return new Study((Integer) source);
        }
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }
}