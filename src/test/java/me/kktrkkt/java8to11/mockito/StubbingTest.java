package me.kktrkkt.java8to11.mockito;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;
import me.kktrkkt.java8to11.junit5.member.MemberNotFoundException;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import me.kktrkkt.java8to11.junit5.study.StudyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StubbingTest {

    @Mock
    MemberService memberService;

    Member member;

    @BeforeEach
    void beforeEach() {
        member = new Member();
        member.setId(1L);
        member.setEmail("kktrkkt@email.com");
    }

    @Test
    void find_member_test() {
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        assertEquals(member, memberService.findById(1L).get());
        assertEquals(Optional.empty(), memberService.findById(2L));
    }

    @Test
    void throw_exception_test() {
        doThrow(MemberNotFoundException.class).when(memberService).findById(anyLong());
        assertThrows(MemberNotFoundException.class, () -> memberService.findById(1L));
    }

}
