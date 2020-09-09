package com.panli.concurrent_programming.part01.chap01;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 实现Runnable接口实现run方法来实现多线程
 */
public class RunnableThread implements Runnable {

    public void run() {
        System.out.println("实现Runnable接口实现run方法来实现多线程");
    }
}
