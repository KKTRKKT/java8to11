package me.kktrkkt.java8to11.concurrent;

public class App {

    public static void main(String[] args) {
        //멀티스레드에서 스레드의 순서는 보장하지 못한다.
        Thread thread = new MyThread();
        thread.start();



        //메인 스레드
        System.out.println(Thread.currentThread().getName());

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("hello:" + this.getName());
        }
    }
}
