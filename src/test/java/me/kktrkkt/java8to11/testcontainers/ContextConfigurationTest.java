package me.kktrkkt.java8to11.testcontainers;

import lombok.extern.slf4j.Slf4j;
import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import me.kktrkkt.java8to11.junit5.study.StudyService;
import me.kktrkkt.java8to11.junit5.study.StudyStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
// 스프링 테스트 컨테이너가 사용할 설정 파일 또는 컨텍스트를 설정할 수 있다.
@ContextConfiguration(initializers = ContextConfigurationTest.ContainerPropertyInitializer.class)
class ContextConfigurationTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;


    @Autowired
    // 프로퍼티와 프로파일을 담당하는 객체다
    Environment environment;

    // @Value를 사용하면 조회와 동시에 캐스팅이 가능하다.
    @Value("${container.port}")
    int port;

    @Container
    static final GenericContainer<?> postgreSQLContainer = new GenericContainer<>("postgres")
            .withEnv("POSTGRES_DB", "studytest");

    @BeforeEach
    void beforeEach() {
        System.out.println("=======================");
//        System.out.println(environment.getProperty("container.port"));
        System.out.println(port);

        studyRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    @Test
    void createStudy() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("kktrkkt@email.com");

        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        studyService.createStudy(1L, study);
        assertEquals(member.getId(), study.getOwnerId());
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");

        // When
        Study openStudy = studyService.openStudy(study);

        // Then
        assertEquals(StudyStatus.OPENED, openStudy.getStatus());
        assertNotNull(openStudy.getOpenedDateTime());
        then(memberService).should().notify(study);
    }

    // 스프링 ApplicationContext를 동적으로 초기화 할때 사용하는 인터페이스
    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        // 특정 프로퍼티를 활성화하거나, 프로퍼티 소스를 추가할 수 있다.
        @Override
        public void initialize(ConfigurableApplicationContext context) {
            // TestPropertyValues는 프로퍼티 소스를 정의할 때 사용한다.
            TestPropertyValues.of("container.port=" + postgreSQLContainer.getMappedPort(5432))
                    .applyTo(context.getEnvironment());
        }
    }

}