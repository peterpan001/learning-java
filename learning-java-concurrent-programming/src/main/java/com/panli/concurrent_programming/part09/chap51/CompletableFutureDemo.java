package com.panli.concurrent_programming.part09.chap51;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lipan
 * @date 2020-09-18
 * @desc CompletableFuture实现旅游平台问题
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        Set<Integer> prices = demo.getPrices();
        prices.forEach(v -> {
            System.out.println(v);
        });
    }

    private Set<Integer> getPrices() {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(123, prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(456, prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(789, prices));
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3); // 这个方法的作用是把多个 task 汇总，然后可以根据需要去获取到传入参数的这些 task 的返回结果，或者等待它们都执行完毕等。
        try {
            allTasks.get(3, TimeUnit.SECONDS); // 等待3秒如果3秒之内都返回了则直接返回，否则会抛出TimeoutException异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return prices;
    }

    private class Task implements Runnable {
        Integer productId;
        Set<Integer> prices;

        public Task(Integer productId, Set<Integer> prices) {
            this.productId = productId;
            this.prices = prices;
        }

        @Override
        public void run() {
            int price = 0;
            double val = Math.random() * 4000;
            try {
                Thread.sleep((long) val);
                price = (int) val;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }
}
