package me.kktrkkt.java8to11.excutors;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        baseRunnable();
//        baseCallable();
//        completableFuture1();
        System.out.println("---------------------thenCompose--------------------------");

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // CompletableFuture을 연결해서 사용한다.
        CompletableFuture<String> helloWorld = hello.thenCompose(App::getWorld);
        System.out.println(helloWorld.get());

        System.out.println("---------------------thenCombine--------------------------");

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        // CompletableFuture의 각각의 결과값을 받아서 계산한다.
        CompletableFuture<String> helloWorld2 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(helloWorld2.get());

        System.out.println("---------------------allOf--------------------------");

        CompletableFuture<Integer> hundred = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hundred " + Thread.currentThread().getName());
            return 100;
        });

        // 작업결과는 항상 null이다.
        CompletableFuture<Void> allOf = CompletableFuture.allOf(hello, world, hundred)
                        .thenAccept((r)->{
                            System.out.println(r + " " + hello.join() + " " + world.join()  + " " + hundred.join());
                        });
        System.out.println(allOf.get());

        System.out.println("");

        // 논블로킹으로 모든 작업들의 결과물들을 합친다.
        CompletableFuture[] futures = Stream.of().toArray(CompletableFuture[]::new);
        CompletableFuture<List<Object>> resultList = CompletableFuture.allOf(futures)
                .thenApply(v -> Arrays.stream(futures)
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        resultList.get().forEach(System.out::println);

        System.out.println("---------------------anyOf--------------------------");

        // 가장 빨리 끝나는 작업의 결과물 출력
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(hello, world, hundred)
                .thenApply(r -> r);
        System.out.println(anyOf.get());

        System.out.println("---------------------exceptionally--------------------------");
        boolean throwError = true;

        // 예외 처리
        CompletableFuture<String> kktrkkt = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalStateException();
            }
            return "KKTRKKT";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "error";
        });

        System.out.println(kktrkkt.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + " World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

    private static void completableFuture1() throws InterruptedException, ExecutionException {
        System.out.println("---------------------CompletableFuture--------------------------");

        CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();
        // 작업이 완료되지 않을 경우 설정값
        stringCompletableFuture.complete("hello");
        System.out.println(stringCompletableFuture.get());

        // 위랑 똑같다
        stringCompletableFuture = CompletableFuture.completedFuture("Hello");
        System.out.println(stringCompletableFuture.get());

        // 작업의 리턴값이 없을 경우 runAsync
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(getRunnable("runAsync"));
        runAsync.get();

        // 작업의 리턴값이 있을 경우 supplyAsync
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "supplyAsync" + Thread.currentThread().getName());
        System.out.println(supplyAsync.get());

        // 리턴하는 콜백함수 thenApply
        String supplyAsyncUpper = supplyAsync.thenApply(String::toUpperCase).get();
        System.out.println(supplyAsyncUpper);

        // 리턴이 없는 콜백함수 thenAccept
        supplyAsync.thenAccept(r -> System.out.println(r.toUpperCase())).get();

        // 결과 상관없이 실행 thenRun
        supplyAsync.thenRun(()->{
            System.out.println("isDone! "+Thread.currentThread().getName());
        }).get();

        // executor를 명시해서 사용가능, then*Async
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        supplyAsync.thenRunAsync(()-> System.out.println(Thread.currentThread().getName()), executorService);
        executorService.shutdown();
    }

    private static void baseCallable() throws InterruptedException, ExecutionException {
        System.out.println("---------------------Callable과 Future--------------------------");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // Callable을 통해 스레드 작업 후 결과값을 얻을 수 있다.
        Callable<String> hello = getCallable(2000L, "Hello");

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

        System.out.println("---------------------invokeAll--------------------------");
        Callable<String> java = getCallable(3000L, "Java");
        Callable<String> mango = getCallable(1000L, "Mango");

        System.out.println(LocalDateTime.now());
        // invokeAll은 리스트에 있는 모든 작업이 종료될때까지 기다린다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, mango));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        System.out.println(LocalDateTime.now());

        System.out.println("---------------------invokeAny--------------------------");

        // 3개 작업을 동시에 실행하기 위해서 3개의 스레드 사용
        ExecutorService executorsService2 = Executors.newFixedThreadPool(3);

        System.out.println(LocalDateTime.now());
        // invokeAny는 모든 작업중 가장 빨리 종료되는 작업의 결과를 반환한다.
        String any = executorsService2.invokeAny(Arrays.asList(hello, java, mango));
        System.out.println(any);
        System.out.println(LocalDateTime.now());
    }

    private static Callable<String> getCallable(long millis, String hello1) {
        Callable<String> hello = () -> {
            Thread.sleep(millis);
            return hello1;
        };
        return hello;
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
