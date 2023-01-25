package me.kktrkkt.java8to11.optional;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass springBoot = springClasses.get(0);
        Progress progress = springBoot.getProgress();
        //기존 null 처리 방법
        if(progress != null){
            System.out.println(progress.getStudyDuration());
        }
    }
}
