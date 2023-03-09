package me.kktrkkt.java8to11.junit5.member;

import me.kktrkkt.java8to11.junit5.domain.Member;
import me.kktrkkt.java8to11.junit5.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void notify(Study newStudy);

    void valid();

    void notify(Optional<Member> member);
}
