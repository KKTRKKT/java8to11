package me.kktrkkt.java8to11.junit5.domain;

import me.kktrkkt.java8to11.junit5.study.StudyStatus;

import java.time.LocalDateTime;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;

    private final int limit;

    private String name;

    private Member owner;

    private LocalDateTime openedDateTime;

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if(limit <= 0){
            throw new IllegalArgumentException("limit은 0보다 커야됩니다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Member member) {
        this.owner = member;
    }

    public Member getOwner() {
        return owner;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }

    public void open() {
        status = StudyStatus.OPENED;
        openedDateTime = LocalDateTime.now();
    }
}
