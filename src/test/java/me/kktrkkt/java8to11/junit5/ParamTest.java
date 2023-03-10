package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.study.StudyStatus;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParamTest {

    // 매개변수들을 전달할 수 있는 테스트다
    @ParameterizedTest(name = "[{index}] = {arguments} {displayName}")
    // 3번의 테스트를 실행한다
    @ValueSource(strings = {"파란", "하늘", "꿈"})
    // null값과 비어있는 데이터를 추가한다. 2번의 테스트를 실행한다
    @NullAndEmptySource
    // source에서 선언된 데이터가 순서대로 매개변수에 대입된다
    void parameterized_test(String value) {
        System.out.println(value);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    // @ConvertWith를 통해 주어진 source를 다른 객체로 변환할 수 있다
    void simple_argument_converter_test(@ConvertWith(StudyArgumentConverter.class) Study study) {
        System.out.println(study);
    }

    // 하나의 매개변수만 주어질때 사용할 수 있다
    static class StudyArgumentConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(targetType, Study.class, "Can only convert Study");
            return new Study((Integer) source);
        }
    }

    @ParameterizedTest
    @CsvSource(value={"1, '스프링 스터디'", "2, '자바 스터디'"})
    // 두개 이상의 매개변수를 변환할 때는 @AggregateWith를 사용한다.
    void parameterized_test(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    // 두개 이상의 매개변수를 변환할때 사용한다
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    @ParameterizedTest
    @MethodSource("provideStudy")
    void method_source_test(Study study) {
        System.out.println(study);
    }

    static Stream<Arguments> provideStudy(){
        return Stream.of(
                Arguments.of(new Study(1, "스프링 스터디")),
                Arguments.of(new Study(2, "자바 스터디"))
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv-file-source-test.csv")
    void csv_file_source_test(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    @ParameterizedTest
    @EnumSource(StudyStatus.class)
    void enum_source_test(StudyStatus status) {
        System.out.println(status);
    }
}