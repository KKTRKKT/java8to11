package me.kktrkkt.java8to11.testcontainers;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import me.kktrkkt.java8to11.junit5.study.StudyService;
import me.kktrkkt.java8to11.junit5.domain.StudyStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
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
// @Container를 사용하기 위해서 선언한다
@Testcontainers
class TestContainerTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

    // BeforeAll과 AfterALL에서 container를 시작하고 종료하는 작업을 대신해준다.
    @Container
    // static을 붙이지 않으면 테스트마다 도커를 새로 만든다.
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("studytest");


    @BeforeEach
    void beforeEach() {
        studyRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
//        postgreSQLContainer.start();
//        System.out.println(postgreSQLContainer.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
//        postgreSQLContainer.stop();
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