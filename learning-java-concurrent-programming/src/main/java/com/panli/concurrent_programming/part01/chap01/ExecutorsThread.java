package com.panli.concurrent_programming.part01.chap01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 借助线程池创建多线程，其本质是借助线程工厂创建线程的
 */
public class ExecutorsThread {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // service.execute(new RunnableThread());
            service.execute(new ExtendsThread());
        }
    }
}
