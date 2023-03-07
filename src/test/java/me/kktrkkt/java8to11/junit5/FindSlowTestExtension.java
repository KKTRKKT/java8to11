package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeEachCallback, AfterEachCallback {

    private static final long THRESHOLD = 1000L;
    private final String START_TIME = "START_TIME";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String className = context.getClass().getName();
        String methodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(className, methodName));
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Method method = context.getRequiredTestMethod();
        SlowTest annotation = method.getAnnotation(SlowTest.class);
        String name = method.getName();

        ExtensionContext.Store store = getStore(context);
        long startTime = (long) store.get(START_TIME);
        long duration = System.currentTimeMillis() - startTime;

        if(duration > THRESHOLD && annotation == null){
            System.out.printf("Please Consider mark method [%s] with @SlowTest\n", name);
        }
    }
}
