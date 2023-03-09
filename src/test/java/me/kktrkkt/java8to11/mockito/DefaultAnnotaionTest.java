package me.kktrkkt.java8to11.mockito;

import me.kktrkkt.java8to11.junit5.member.MemberService;
import me.kktrkkt.java8to11.junit5.study.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// mockito 확장 모델 선언
@ExtendWith(MockitoExtension.class)
public class DefaultAnnotaionTest {

    // 필드 선언 방식
    @Mock
    MemberService memberService;

    @Test
    // 매개변수 선언 방식
    void mock_test(@Mock StudyRepository repository){
        // 메소드 내에서 선언 방식
        StudyRepository mock = mock(StudyRepository.class);

        // 선언된 mock들의 메소드들은 모두 아무것도 없는 백지 상태라고 보면된다.
        // 메소드들은 빈값을 리턴하며, void는 아무일도 하지 않는다.
        assertNotNull(memberService);
        assertNotNull(repository);
        assertNotNull(mock);
    }
}
