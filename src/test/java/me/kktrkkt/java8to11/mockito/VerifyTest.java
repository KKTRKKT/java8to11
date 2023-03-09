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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VerifyTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository repository;

    StudyService studyService;

    Member member;

    Study springStudy;

    @BeforeEach
    void beforeEach(){
        studyService = new StudyService(memberService, repository);

        springStudy = new Study(10, "스프링 스터디");

        member = new Member();
        member.setId(1L);
        member.setEmail("kktrkkt@email.com");

        doReturn(Optional.of(member)).when(memberService).findById(1L);
    }

    @Test
    void verify_test(){
        Study study = studyService.createStudy(1L, springStudy);

        // notify가 호출되어야 한다
        verify(memberService).findById(1L);
        // notify를 1번 호출해야 한다
        verify(memberService, times(1)).findById(1L);
        // valid를 호출하지 말아야 한다
        verify(memberService, never()).valid();
        // notify를 적어도 0번 이상 호출해야한다.
        verify(memberService, atLeast(0)).notify(study);
        // notify는 최대 1번까지 호출할 수 있다.
        verify(memberService, atMost(1)).notify(study);
        // notify는 0.1초 내에 호출이 종료되어야 한다.
        verify(memberService, timeout(100)).notify(study);

    }

    @Test
    void inOrder_test(){
        Study study = studyService.createStudy(1L, springStudy);

        InOrder inOrder = inOrder(memberService);

        // findById를 호출하고 notify를 호출해야한다.
        inOrder.verify(memberService).findById(1L);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(Optional.of(member));

        // notify 후에는 호출되는 메서드가 없어야 한다.
        inOrder.verifyNoMoreInteractions();
    }
}
