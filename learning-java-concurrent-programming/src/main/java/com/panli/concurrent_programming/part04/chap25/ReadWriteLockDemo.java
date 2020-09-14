package com.panli.concurrent_programming.part04.chap25;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 演示读写锁的用法
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> write()).start();
        new Thread(() -> write()).start();
    }
}
