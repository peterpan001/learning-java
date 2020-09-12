package com.panli.concurrent_programming.part01.chap05;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lipan
 * @date 2020-09-12
 * @desc 使用阻塞队列模拟生产者消费者
 * @solution:  ArrayBlockingQueue 在背后完成了很多工作，比如队列满了就去阻塞生产者线程，队列有空就去唤醒生产者线程等
 */
public class BlockQueueProducerConsumer {

    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {
            while (true) {
                try {
                    queue.put(new Object());
                    System.out.println(Thread.currentThread().getName() + "生产数据.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = () -> {
            while (true) {
                try {
                    queue.take();
                    System.out.println(Thread.currentThread().getName() + "消费数据.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
