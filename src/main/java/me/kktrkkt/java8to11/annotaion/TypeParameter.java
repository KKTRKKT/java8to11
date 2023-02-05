package me.kktrkkt.java8to11.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 제네릭 부분에만 사용할 수 있다.
@Target(ElementType.TYPE_PARAMETER)
public @interface TypeParameter {
}
