package com.panli.concurrent_programming.part03.chap09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipan
 * @date 2020-09-13
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            service.execute(new Task());
        }
        System.out.println(Thread.currentThread().getName());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name:" + Thread.currentThread().getName());
        }
    }
}