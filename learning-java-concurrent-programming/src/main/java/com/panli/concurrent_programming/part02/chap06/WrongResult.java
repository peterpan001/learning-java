package com.panli.concurrent_programming.part02.chap06;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 线程不安全问题：运行结果错误
 * @solution  线程不安全原因：i++操作非原子性操作；第一步读取；第二步增加；第三步保存；
 */
public class WrongResult {

    volatile static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        };

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                for (int j = 0; j < 10000; j++) {
//                    i++;
//                }
//            }
//        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
