package com.panli.concurrent_programming.part09.chap51;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipan
 * @date 2020-09-18
 * @desc 线程池实现旅游平台问题：等待3秒就立即返回，获取到几个航空公司的票价就到几个，没有获取到的为null
 */
public class ThreadPoolDemo {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(3); //创建核心线程数量为3的线程池

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        Set<Integer> prices = threadPoolDemo.getPrices();
        prices.forEach(v -> {
            System.out.println(v);
        });
        threadPool.shutdown(); // 关闭线程池
        System.out.println("done");
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        threadPool.submit(new Task(123, prices)); // 借助线程池提交任务
        threadPool.submit(new Task(456, prices)); // 借助线程池提交任务
        threadPool.submit(new Task(789, prices)); // 借助线程池提交任务
        Thread.sleep(3000); // 等待3秒
        return prices; // 返回结果
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
            try {
                double val = Math.random() * 10000; // 随机获取一个值，模拟航空公司机票价格以及接口调用需要等待的时间
                Thread.sleep((long) val);
                price = (int) val;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }
}
