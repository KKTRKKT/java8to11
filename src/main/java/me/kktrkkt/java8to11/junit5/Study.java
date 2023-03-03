package me.kktrkkt.java8to11.junit5;

public class Study {

    final private StudyStatus status = StudyStatus.DRAFT;

    private final int limit;

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
}
