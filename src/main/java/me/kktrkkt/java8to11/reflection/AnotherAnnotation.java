package me.kktrkkt.java8to11.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface AnotherAnnotation {

    String value();

    String name() default "kktrkkt";

    int number() default 100;

}
