package com.panli.concurrent_programming.part01.chap01;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 继承Thread类重写run方法实现多线程
 */
public class ExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread类重写run()来实现多线程");
    }
}
