package me.kktrkkt.java8to11.concurrent;

import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) throws InterruptedException {
        //멀티스레드에서 스레드의 순서는 보장하지 못한다.
        Thread thread = new MyThread();
        thread.start();

        // Runnable 인터페이스를 구현해서 생성
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("annonymous:"+Thread.currentThread().getName());
            }
        });
        thread1.start();

        //자바 8부터는 람다로 간단하게 생성 가능
        Thread thread2 = new Thread(()-> System.out.println("lambda:"+Thread.currentThread().getName()));
        thread2.start();

        //메인 스레드
        System.out.println("main:"+Thread.currentThread().getName());

        //1초마다 메세지를 출력하는 thread 생성
        Thread sleepTest = new Thread(()->{
            while(true){
                System.out.println("sleepTest:"+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    //인터럽트가 발생하면 종료
                    System.out.println("exit!");
                    return;
                }
            }
        });
        sleepTest.start();

        //메인 스레드 3초뒤 sleepTest스레드를 인터럽트한다
//        Thread.sleep(3000L);
        sleepTest.interrupt();

        Thread joinTest = new Thread(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException();
            }
        });
        joinTest.start();

        //join을 호출하면 해당 스레드가 종료될때까지 기다린다.
        System.out.println(LocalDateTime.now());
        joinTest.join();
        System.out.println(LocalDateTime.now());

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("extends:" + this.getName());
        }
    }
}
