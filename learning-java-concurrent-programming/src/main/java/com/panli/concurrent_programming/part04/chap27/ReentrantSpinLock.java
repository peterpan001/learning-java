package com.panli.concurrent_programming.part04.chap27;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lipan
 * @date 2020-09-14
 */
public class ReentrantSpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 重试次数
    private int count = 0;

    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        while (owner.compareAndSet(null, t)) {
            System.out.println("自旋了.");
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        // 只有持有锁的线程才能解锁
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                //此处无需CAS操作，因为没有竞争，因为只有线程持有者才能解锁
                owner.set(null);
            }
        }
    }


    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了自旋锁.");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.lock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁.");
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
