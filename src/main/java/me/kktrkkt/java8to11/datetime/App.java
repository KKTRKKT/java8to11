package me.kktrkkt.java8to11.datetime;

import java.util.Date;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------------ java.util.Date의 문제점-------------------------");
        //이름은 date지만 time을 포함하고 있다.
        Date date = new Date();
        //날짜에서 시간을 가져온다. 그리고 이 시간은 유닉스 시간이다.
        long time = date.getTime();

        System.out.println(date);
        System.out.println(time);

        //불변객체가 아니므로 thread에 안전하지 않다. mutable하다
        Thread.sleep(1000);
        Date after1Seconds = new Date();
        System.out.println(after1Seconds);
        after1Seconds.setTime(time);
        System.out.println(after1Seconds);
    }
}
