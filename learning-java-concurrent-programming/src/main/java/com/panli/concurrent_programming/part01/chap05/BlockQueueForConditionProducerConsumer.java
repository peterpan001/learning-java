package com.panli.concurrent_programming.part01.chap05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lipan
 * @date 2020-09-12
 * @desc 利用阻塞队列+Condition实现生产者消费者模式
 */
public class BlockQueueForConditionProducerConsumer {
    private Queue queue;

    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition(); // 队列没有空的条件
    private Condition notFull = lock.newCondition(); // 队列没有满的条件

    public BlockQueueForConditionProducerConsumer(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void producer(Object o) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (queue.size() == max) { // 使用while是考虑多线程的情况，防止两个都唤醒，一个成功生产数据，另一个再生产数据时队列已满
                    notFull.await();
                }
                queue.add(o);
                System.out.println(Thread.currentThread().getName() + "生产数据.");
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public Object consumer() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (queue.size() == 0) {  // 使用while是考虑多线程的情况，防止两个都唤醒，一个成功消费数据，另一个再消费数据时队列已空
                    notEmpty.await();
                }
                queue.remove();
                System.out.println(Thread.currentThread().getName() + "消费数据");
                notFull.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BlockQueueForConditionProducerConsumer bqcpc = new BlockQueueForConditionProducerConsumer(16);
        Runnable producer = () -> {
            try {
                bqcpc.producer(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(producer).start();

        Runnable consumer = () -> {
            try {
                bqcpc.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(consumer).start();
    }
}
