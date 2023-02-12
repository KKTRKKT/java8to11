package me.kktrkkt.java8to11.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {

    public static void main(String[] args) throws IOException {
        new ByteBuddy().redefine(Moja.class)
                .method(named("pullOut"))
                .intercept(FixedValue.value("!!Rabit"))
                .make().saveIn(new File("/Users/lsh/Documents/GitHub/java8to11/target/classes"));

        // 두번 실행시켜야 적용이 된다.
        // 첫번째는 기존의 Moja클래스를 미리 로드했기 공백이 나온다.
        // 두번째부터는 바이트코드가 조작된 결과가 나오게된다.
        System.out.println(new Moja().pullOut());
    }
}
