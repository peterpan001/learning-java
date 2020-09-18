package com.panli.concurrent_programming.part07.chap40;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lipan
 * @date 2020-09-16
 */
public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService services = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            services.submit(new Task(counter));
        }
        Thread.sleep(1000);
        System.out.println(counter.sum());
        services.shutdown();
    }

    static class Task implements Runnable {
        private final LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }


        @Override
        public void run() {
            counter.increment();

            int i  = 0;
        }
    }
}
