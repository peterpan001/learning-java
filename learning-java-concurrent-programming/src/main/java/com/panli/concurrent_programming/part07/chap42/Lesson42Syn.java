package com.panli.concurrent_programming.part07.chap42;

/**
 * @author lipan
 * @date 2020-09-16
 * @desc sync加锁解决线程不安全问题，对象锁
 */
public class Lesson42Syn implements Runnable{
    static int value = 0;

    @Override
    public void run() {
        for(int i = 0;  i < 10000;i++){
            synchronized (this){
                value++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Lesson42Syn();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(value);
    }
}
