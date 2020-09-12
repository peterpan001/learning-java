package com.panli.concurrent_programming.part01.chap05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lipan
 * @date 2020-09-12
 * @desc 使用condition实现生产者消费者模式
 */
public class BlockQueueForConditionProducerConsumer_1 {

    private static final int MAX_CAP = 1;
    static Queue queue = new LinkedList();
    static ReentrantLock lock = new ReentrantLock();
    static Condition notFull = lock.newCondition();
    static Condition notEmpty = lock.newCondition();

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (queue.size() == MAX_CAP) {
                        notFull.await();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.add(new Object());
                    System.out.println(Thread.currentThread().getName() + "生产数据.");
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (queue.size() == 0) {
                        notEmpty.await();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.remove();
                    System.out.println(Thread.currentThread().getName() + "消费数据.");
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

}
