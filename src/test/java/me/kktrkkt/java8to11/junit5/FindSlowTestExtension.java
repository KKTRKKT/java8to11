package me.kktrkkt.java8to11.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeEachCallback, AfterEachCallback {

    private final long threshold;

    private final String START_TIME = "START_TIME";

    public FindSlowTestExtension(long threshold) {
        this.threshold = threshold;
    }

    // 각각의 테스트 실행전에 작업할 내용을 명시한다
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String className = context.getClass().getName();
        String methodName = context.getRequiredTestMethod().getName();
        // 클래스명과 메소드명으로 store 조회해서, store가 있으면 반환하고 없으면 생성해서 반환한다.
        return context.getStore(ExtensionContext.Namespace.create(className, methodName));
    }

    // 각각의 테스트 실행후에 작업할 내용을 명시한다
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Method method = context.getRequiredTestMethod();
        SlowTest annotation = method.getAnnotation(SlowTest.class);
        String name = method.getName();

        ExtensionContext.Store store = getStore(context);
        long startTime = (long) store.get(START_TIME);
        long duration = System.currentTimeMillis() - startTime;

        if(duration > threshold && annotation == null){
            System.out.printf("Please Consider mark method [%s] with @SlowTest\n", name);
        }
    }
}
