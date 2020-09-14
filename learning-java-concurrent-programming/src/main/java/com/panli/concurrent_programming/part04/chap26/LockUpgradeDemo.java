package com.panli.concurrent_programming.part04.chap26;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lipan
 * @date 2020-09-14
 * @Desc 锁不支持升级demo，程序会一直卡在rwl.writeLock().lock();
 */
public class LockUpgradeDemo {

    final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        upgrade();
    }

    public static void  upgrade(){
        rwl.readLock().lock();
        System.out.println("获取到了读锁.");
        rwl.writeLock().lock();
        System.out.println("获取到了写锁.");
    }
}
