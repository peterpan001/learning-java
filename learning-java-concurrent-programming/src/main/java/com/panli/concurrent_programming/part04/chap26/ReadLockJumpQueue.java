package com.panli.concurrent_programming.part04.chap26;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc ReentrantReadWriteLock 的实现选择了“不允许插队”的策略，这就大大减小了发生“饥饿”的概率
 */
public class ReadLockJumpQueue {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁.");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> read(), "Thread-2").start();
        new Thread(() -> read(), "Thread-4").start();
        new Thread(() -> write(), "Thread-3").start();
        new Thread(() -> read(), "Thread-5").start();
    }
}
