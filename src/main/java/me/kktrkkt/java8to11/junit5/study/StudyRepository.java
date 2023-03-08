package me.kktrkkt.java8to11.junit5.study;

import me.kktrkkt.java8to11.junit5.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
