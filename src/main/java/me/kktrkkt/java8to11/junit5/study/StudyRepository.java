package me.kktrkkt.java8to11.junit5.study;

import me.kktrkkt.java8to11.junit5.domain.Study;

public interface StudyRepository {
    Study save(Study study);
}
