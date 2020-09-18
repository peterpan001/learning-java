package com.panli.concurrent_programming.part09.chap51;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipan
 * @date 2020-09-18
 * @desc 线程池实现旅游平台问题
 */
public class ThreadPoolDemo {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        Set<Integer> prices = threadPoolDemo.getPrices();
        prices.forEach(v -> {
            System.out.println(v);
        });
        System.out.println("done");
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        threadPool.submit(new Task(123, prices));
        threadPool.submit(new Task(456, prices));
        threadPool.submit(new Task(789, prices));
        Thread.sleep(3000);
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
            try {
                double val = Math.random() * 10000;
                Thread.sleep((long) val);
                price = (int) val;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }
}
