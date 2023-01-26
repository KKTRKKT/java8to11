package me.kktrkkt.java8to11.optional;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    private Progress progress;

//    private Optional<Progress> progress; X progrss 자체가 null일 수 있음

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
//        return null X 클라이언트측에서 null 체크를
//        return Optional.empty() O null 대신 리턴
        return Optional.ofNullable(progress);
    }

//    public void setProgress(Optional<Progress> progress) X 클라이언트에서 null을 던질 수 있음
    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "OnlineClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                '}';
    }
}
