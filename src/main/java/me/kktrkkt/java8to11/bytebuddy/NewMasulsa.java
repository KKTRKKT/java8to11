package me.kktrkkt.java8to11.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class NewMasulsa {

    public static void main(String[] args) throws IOException {
        // 런타임에 클래스 구현을 변경하는 방법
        ClassLoader classLoader = NewMasulsa.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);
        new ByteBuddy().redefine(typePool.describe("me.kktrkkt.java8to11.bytebuddy.Moja").resolve(), ClassFileLocator.ForClassLoader.of(classLoader))
                .method(named("pullOut"))
                .intercept(FixedValue.value("!!Rabit"))
                .make().saveIn(new File("/Users/lsh/Documents/GitHub/java8to11/target/classes"));

        System.out.println(new Moja().pullOut());
    }
}
