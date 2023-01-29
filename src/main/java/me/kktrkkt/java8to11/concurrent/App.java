package me.kktrkkt.java8to11.concurrent;

public class App {

    public static void main(String[] args) {
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

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("extends:" + this.getName());
        }
    }
}
