package me.kktrkkt.java8to11.mockito;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import me.kktrkkt.java8to11.junit5.study.StudyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BDDAPITest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository repository;

    StudyService studyService;

    Member member;

    @BeforeEach
    void beforeEach(){
        studyService = new StudyService(memberService, repository);

        member = new Member();
        member.setId(1L);
        member.setEmail("kktrkkt@email.com");
    }

    @Test
    void bdd_api_test(){
        Study springStudy = new Study(10, "스프링 스터디");

        // Given
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        // When
        Study study = studyService.createStudy(1L, springStudy);
        // Then
        InOrder inOrder = inOrder(memberService);
        then(memberService).should(inOrder).findById(1L);
        then(memberService).should(inOrder).notify(study);
        then(memberService).should(inOrder).notify(Optional.of(member));
        then(memberService).shouldHaveNoMoreInteractions();
    }
}
