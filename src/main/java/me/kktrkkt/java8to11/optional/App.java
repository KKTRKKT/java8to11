package me.kktrkkt.java8to11.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass springBoot = springClasses.get(0);
//        Progress progress = springBoot.getProgress();
//        //기존 null 처리 방법
//        if(progress != null){
//            System.out.println(progress.getStudyDuration());
//        }

        //Optional은 리턴값으로만 사용
        Optional<Progress> progress = springBoot.getProgress();
        progress.ifPresent(p-> System.out.println(p.getStudyDuration()));

        //기본형은 전용 Optinoal 사용.
        OptionalInt optionalInt = OptionalInt.of(10);
        optionalInt.ifPresent(System.out::println);
    }
}
