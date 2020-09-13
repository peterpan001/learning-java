package com.panli.concurrent_programming.part02.chap06;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 线程不安全性问题：活跃性问题-死锁，相互等待对方释放自己持有的锁，互不相让.
 */
public class MayDeadLock {

    Object o1 = new Object();
    Object o2 = new Object();

    public void thread1() throws InterruptedException {
        synchronized (o1) {
            Thread.sleep(5000);
            synchronized (o2) {
                System.out.println("线程Thread1成功拿到锁.");
            }
        }
    }

    public void thread2() throws InterruptedException {
        synchronized (o2) {
            Thread.sleep(5000);
            synchronized (o1) {
                System.out.println("线程Thread2成功拿到锁.");
            }
        }
    }

    public static void main(String[] args) {
        MayDeadLock mayDeadLock = new MayDeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
