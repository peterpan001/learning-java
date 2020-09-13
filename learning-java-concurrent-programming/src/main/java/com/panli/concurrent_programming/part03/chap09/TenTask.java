package com.panli.concurrent_programming.part03.chap09;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 创建10个线程，线程启动顺序是乱序的
 */
public class TenTask {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name:" + Thread.currentThread().getName());
        }
    }
}
