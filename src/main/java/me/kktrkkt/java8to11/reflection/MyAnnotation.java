package me.kktrkkt.java8to11.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    String value();

    String name() default "kktrkkt";

    int number() default 100;

}
