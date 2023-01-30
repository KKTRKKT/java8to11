package me.kktrkkt.java8to11.excutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(getRunnable("Hello "));
        //종료를 명시해야됨
        //graceful shutdown, 스레드가 하던일을 마치고 종료
        executorService.shutdown();
        //바로 종료
//        executorService.shutdownNow();

        // thread 2개로 실행
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("first "));
        executorService2.submit(getRunnable("second "));
        executorService2.submit(getRunnable("third "));
        executorService2.submit(getRunnable("fourth "));
        executorService2.shutdown();

    }

    private static Runnable getRunnable(String message) {
        return ()-> System.out.println(message + Thread.currentThread().getName());
    }
}
