package me.kktrkkt.java8to11.excutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
//        baseRunnable();
    }

    private static void baseRunnable() {
        System.out.println("---------------------ExecutorService--------------------------");
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

        System.out.println("---------------------ScheduledExecutorService--------------------------");

        // 일정에 맞춰서 스레드 실행
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 3초뒤에 실행
        scheduledExecutorService.schedule(getRunnable("schedule "), 3, TimeUnit.SECONDS);
        // 3초뒤에 실행후 2초마다 실행
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("rateSchedule "), 3, 2, TimeUnit.SECONDS);
        // 6초후에 종료
        scheduledExecutorService.schedule(scheduledExecutorService::shutdown, 6, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return ()-> System.out.println(message + Thread.currentThread().getName());
    }
}
