package me.kktrkkt.java8to11.excutors;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        baseRunnable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // Callable을 통해 스레드 작업 후 결과값을 얻을 수 있다.
        Callable<String> hello = () -> {
            Thread.sleep(2000);
            return "Hello";
        };
        Future<String> helloFuture = executorService.submit(hello);

        // isDone 작업이 종료되었는지 확인한다
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        // 블로킹, 값을 가져올때까지 메인스레드는 대기한다.
        helloFuture.get();
        // 실행중인 작업을 중단한다. false면 실행중인 작업 마치고 중단, true면 바로 중단
//        helloFuture.cancel(false);
        // 실행도중 cancel이 되면 결과값을 가져올 수 없으므로 Exception이 발생한다.(true, false 모두)
//        helloFuture.get();

        System.out.println("End!");
        System.out.println(helloFuture.isDone());


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
