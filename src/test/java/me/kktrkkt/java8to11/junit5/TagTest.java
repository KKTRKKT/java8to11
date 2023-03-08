package me.kktrkkt.java8to11.junit5;

import me.kktrkkt.java8to11.junit5.domain.Study;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TagTest {

    @Test
    @Tag("fast")
    void tag_fast() {
        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @Tag("slow")
    void tag_slow() {
        Study study = new Study(10);
        assertNotNull(study);
    }
}