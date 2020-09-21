package com.panli.concurrent_programming.part09.chap51;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lipan
 * @date 2020-09-18
 * @desc CountDownLatch实现旅游平台问题：
 */
public class CountDownLatchDemo {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo downLatchDemo = new CountDownLatchDemo();
        Set<Integer> prices = downLatchDemo.getPrices();
        prices.forEach(v -> {
            System.out.println(v);
        });
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CountDownLatch countDownLatch = new CountDownLatch(3); // 设置 CountDownLatch为3，即调用3次countDownLatch.countDown()方法才能触发
        threadPool.submit(new Task(123, prices, countDownLatch));
        threadPool.submit(new Task(456, prices, countDownLatch));
        threadPool.submit(new Task(789, prices, countDownLatch));
        countDownLatch.await(3, TimeUnit.SECONDS); // 最多等待3秒返回结果，如果不设置时间的话，就会等到countDownLatch为0时才能触发
        return prices;
    }

    private class Task implements Runnable {

        Integer productId;
        Set<Integer> prices;
        CountDownLatch countDownLatch;

        public Task(Integer productId, Set<Integer> prices, CountDownLatch countDownLatch) {
            this.productId = productId;
            this.prices = prices;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                double val = Math.random() * 4000;
                Thread.sleep((long) val);
                price = (int) val;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
            countDownLatch.countDown();
        }
    }
}
