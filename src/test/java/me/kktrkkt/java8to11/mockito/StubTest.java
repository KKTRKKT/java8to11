package me.kktrkkt.java8to11.mockito;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.member.MemberNotFoundException;
import me.kktrkkt.java8to11.junit5.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StubTest {

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
    void when_test() {
        // findById에 1L 파라미터를 줄시 member를 반환하도록 설정한다
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        assertEquals(member, memberService.findById(1L).get());
        assertEquals(Optional.empty(), memberService.findById(2L));
    }

    @Test
    void doReturn_test() {
        // findById에 1L 파라미터를 줄시 member를 반환하도록 설정한다
        doReturn(Optional.of(member)).when(memberService).findById(any());

        assertEquals(member, memberService.findById(1L).get());
        assertEquals(Optional.empty(), memberService.findById(2L));
    }

    @Test
    void doThrow_test() {
        // findById를 호출하면 MemberNotFoundException을 던진다
        doThrow(MemberNotFoundException.class).when(memberService).findById(anyLong());
        assertThrows(MemberNotFoundException.class, () -> memberService.findById(1L));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L})
    void doAnswer_test(long id) {
        // 가져오는 id에 따라 member id값이 변경된다
        doAnswer(invocation->{
            member.setId(invocation.getArgument(0));
            return Optional.of(member);
        }).when(memberService).findById(anyLong());

        assertEquals(id, memberService.findById(id).get().getId());
    }

    @Test
    void doNothing_test() {
        // 아무것도 수행하지 않는다 오직 void 메소드에만 사용할 수 있다.
//        doNothing().when(memberService).findById(2L);

//        assertEquals(Optional.empty(), memberService.findById(2L));
    }

}
