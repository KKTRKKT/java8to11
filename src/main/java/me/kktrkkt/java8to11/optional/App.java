package me.kktrkkt.java8to11.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

//        optionalExample(springClasses);

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(s -> s.getTitle().startsWith("spring"))
                .findFirst();

        System.out.println("---------------isPresent--------------------");

        //isPresent 값이 있으면 true 반환
        boolean present = optional.isPresent();
        System.out.println(present);

        System.out.println("---------------ifPresent--------------------");

        //ifPresnet 값이 있으면 메소드 실행
        optional.ifPresent(System.out::println);

        System.out.println("---------------orElse--------------------");

        //orElse 값이 없으면 other 출력
        //createOnlineClass()는 값이 없을때도 실행된다.
        Optional.empty().orElse(createOnlineClass());
        OnlineClass onlineClass = optional.orElse(createOnlineClass());
        System.out.println(onlineClass.getTitle());

        System.out.println("---------------orElseGet--------------------");

        //orElseGet 값이 없는 경우에 메소드 실행
        //값이 없으면 createOnlineClass()는 실행되지 않는다
        Optional.empty().orElseGet(App::createOnlineClass);
        OnlineClass onlineClass1 = optional.orElseGet(App::createOnlineClass);
        System.out.println(onlineClass.getTitle());

        System.out.println("---------------orElseThrow--------------------");

        //orElseThrow 값이 없는 경우에 기본적으로 NoSuchElementExption을 던진다
//        Optional.empty().orElseThrow(IllegalArgumentException::new);

        System.out.println("---------------filter--------------------");

        //filter 조건에 맞으면 값이 있는 Optiaonl, 없으면 빈 Optaoinl 반환
        Optional<OnlineClass> onlineClass2 = optional.filter(OnlineClass::isClosed);
        System.out.println(onlineClass2.isPresent());

        System.out.println("---------------map--------------------");

        //map 값이 있으면 매핑함수를 적용한 값을 Optional에 실어서 반환
        //만약 매핑함수 적용 반환값이 Optional일 경우 Optioanl<Optaionl<T>>이 될 수 있어 flatMap을 사용
        optional.map(OnlineClass::getId).ifPresent(System.out::println);
//        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);

        System.out.println("---------------flatMap--------------------");

        //flatMap Optional이 리턴되는 경우에만 사용한다.
        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);
        progress.ifPresent(p-> System.out.println(p.getStudyDuration()));

    }

    private static OnlineClass createOnlineClass() {
        System.out.println("create new Online Class");
        return new OnlineClass(6, "new Online Class", false);
    }

    private static void optionalExample(List<OnlineClass> springClasses) {
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
