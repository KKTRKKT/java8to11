package me.kktrkkt.java8to11.testcontainers;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import me.kktrkkt.java8to11.junit5.study.StudyService;
import me.kktrkkt.java8to11.junit5.domain.StudyStatus;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
class DockerComposeContainerTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

    // dockerComposeContainer는 classRule을 사용한다
    @ClassRule
    static final DockerComposeContainer<?> environment =
            new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yml"))
                    // 내부포트는 5432, 포트가 연결되고 10초를 기다린다.
                    .withExposedService("study-db", 5432,
                            Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)));

    @BeforeEach
    void beforeEach() {
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

}