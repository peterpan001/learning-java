package com.panli.concurrent_programming.part01.chap02.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author lipan
 * @date 2020-09-10
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(500);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了.");
            Thread.sleep(100);
        }

        System.out.println("消费者不需要更多数据了。");

        // 一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况却停不下来
        // 因为生产者被阻塞在storage.put(num)这里了，无法进行下一次判断
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}
