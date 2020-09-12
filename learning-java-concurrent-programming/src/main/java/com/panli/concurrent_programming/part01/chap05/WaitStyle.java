package com.panli.concurrent_programming.part01.chap05;

import java.util.LinkedList;

/**
 * @author lipan
 * @date 2020-09-12
 */
public class WaitStyle {
    public static void main(String[] args) {
        MyBlockingQueue mbq = new MyBlockingQueue(10);
        Producer producer = new Producer(mbq);
        Consumer consumer = new Consumer(mbq);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {
    private MyBlockingQueue storage;

    public Producer(MyBlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private MyBlockingQueue storage;

    public Consumer(MyBlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyBlockingQueue {
    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }
        storage.add(new Object());
        System.out.println(Thread.currentThread().getName() + "线程生产数据.");
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            wait();
        }
        System.out.println(storage.remove());
        System.out.println(Thread.currentThread().getName() + "线程消费数据.");
        notifyAll();
    }
}
