package com.panli.concurrent_programming.part07.chap42;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lipan
 * @date 2020-09-16
 * @desc 原子类解决线程不安全问题
 */
public class Lesson42Atomic implements Runnable {

    static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            atomicInteger.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Lesson42Atomic();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicInteger.get());
    }
}
