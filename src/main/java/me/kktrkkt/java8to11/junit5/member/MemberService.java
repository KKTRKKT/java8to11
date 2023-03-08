package me.kktrkkt.java8to11.junit5.member;

import me.kktrkkt.java8to11.junit5.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
}
