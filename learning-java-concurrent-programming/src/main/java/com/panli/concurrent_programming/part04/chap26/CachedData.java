package com.panli.concurrent_programming.part04.chap26;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 锁降级功能代码演示
 */
public class CachedData {

    Object data;
    volatile boolean cachedValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        CachedData cachedData = new CachedData();
        cachedData.processCachedData();
    }

    void processCachedData() {
        rwl.readLock().lock();
        if (!cachedValid) {
            // 在获取读锁之前，必须首先释放读锁
            rwl.readLock().unlock();
            System.out.println("释放读锁");
            rwl.writeLock().lock();
            System.out.println("写锁");
            try {
                // 这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他线程修改了数据。
                if (!cachedValid) {
                    data = new Object();
                    System.out.println("初始化对象");
                    cachedValid = true;
                }
                // 在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                rwl.readLock().lock();
                System.out.println("读锁");
            } finally {
                // 释放了写锁，但是依然持有读锁
                rwl.writeLock().unlock();
                System.out.println("释放写锁.");
            }
        }

        try {
            System.out.println(data);
        } finally {
            // 释放读锁
            rwl.readLock().unlock();
            System.out.println("释放读锁.");
        }
    }
}
