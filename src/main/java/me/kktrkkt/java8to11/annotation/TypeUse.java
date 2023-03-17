package me.kktrkkt.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 타입을 사용하는 모든 곳에서 사용할 수 있다.
@Target(ElementType.TYPE_USE)
public @interface TypeUse {
}
