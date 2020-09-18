package com.panli.concurrent_programming.part07.chap40;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lipan
 * @date 2020-09-16
 */
public class AtomicLongDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService execotors = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            execotors.submit(new Task(counter));
        }
        Thread.sleep(2000);
        System.out.println(counter.get());
        execotors.shutdown();
    }

    static class Task implements Runnable {

        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}
