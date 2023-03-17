package me.kktrkkt.java8to11.annotation;

import java.util.List;

@TypeUse
public class App {

    // void 타입에는 사용할 수 없다
//    @TypeUse
    public static void main(@TypeUse String[] args) {
        List<@TypeUse String> names = List.of("name");
    }

    @TypeUse
    static private class Chicken<@TypeUse @TypeParameter T> {

    }
}
