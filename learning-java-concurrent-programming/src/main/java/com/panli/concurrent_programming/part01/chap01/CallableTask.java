package com.panli.concurrent_programming.part01.chap01;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 实现Callable接口实现call方法创建带有返回值的多线程
 */
public class CallableTask implements Callable<Integer> {

    public Integer call() throws Exception {
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 提交任务，并用 Future 提交返回结果
        Future<Integer> submit = service.submit(new CallableTask());
        System.out.println(submit);
    }
}
