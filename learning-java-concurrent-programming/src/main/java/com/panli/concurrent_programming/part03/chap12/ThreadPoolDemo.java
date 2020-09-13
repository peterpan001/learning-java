package com.panli.concurrent_programming.part03.chap12;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 线程池 demo
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lipan
 * @date 2020-09-13
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        // 固定数量线程池
        ExecutorService FixedService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            FixedService.execute(new Task());
        }
        System.out.println("FixedService" + Thread.currentThread().getName());

        // 带缓存的线程池
        ExecutorService cachedService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            cachedService.execute(new Task());
        }
        System.out.println("cachedService" + Thread.currentThread().getName());

        // 可以定时执行的线程池
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            scheduledService.schedule(new Task(), 10, TimeUnit.SECONDS);
        }
        System.out.println("cachedService" + Thread.currentThread().getName());

        // 单一线程池
        ExecutorService singleThreadService = Executors.newSingleThreadExecutor();

        // 支持调度的单一线程池
        ScheduledExecutorService singleThreadScheduledService = Executors.newSingleThreadScheduledExecutor();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name:" + Thread.currentThread().getName());
        }
    }
}