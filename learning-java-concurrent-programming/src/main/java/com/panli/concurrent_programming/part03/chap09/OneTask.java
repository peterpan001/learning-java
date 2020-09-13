package com.panli.concurrent_programming.part03.chap09;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 创建单一线程
 */
public class OneTask {
    public static void main(String[] args) {

        Thread thread0 = new Thread(new Task());
        thread0.start();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread Name:" + Thread.currentThread().getName() + "");
        }
    }
}
