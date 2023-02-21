package me.kktrkkt.java8to11.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        Arrays.stream(instance.getClass().getDeclaredFields()).forEach(f->{
            f.setAccessible(true);
            Inject annotation = f.getAnnotation(Inject.class);
            if(annotation != null){
                try {
                    Class<?> type = f.getType();
                    f.set(instance, createInstance(type));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return instance;
    }

    private static <T> T createInstance(Class<T> classType){
        try {
            return classType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
