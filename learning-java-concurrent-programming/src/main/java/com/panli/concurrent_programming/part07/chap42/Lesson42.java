package com.panli.concurrent_programming.part07.chap42;

/**
 * @author lipan
 * @date 2020-09-16
 */
public class Lesson42 implements Runnable {

    static int value = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            value++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Lesson42();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(value);
    }
}
