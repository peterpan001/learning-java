package com.panli.concurrent_programming.part01.chap01.source_code;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 借助匿名内部类实现线程
 */
public class AnonymousInnerClassThread {

    public static void main(String[] args) {

        // 匿名内部类实现线程
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        // lambda表达式实现多线程
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
