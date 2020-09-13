package com.panli.concurrent_programming.part02.chap07;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 访问共享变量时需要注意线程安全问题
 */
public class ThreadNotSafe {
    static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    i++;
                }
            }
        };

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
