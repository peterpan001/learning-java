package com.panli.concurrent_programming.part01.chap02.producer_consumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 生产者
 */
public class Producer implements Runnable {

    // volatile标记位不适合停止线程的场景
    public volatile boolean canceled = false;
    BlockingQueue storage;
    public Producer(BlockingQueue storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 1000000 && !canceled) {
                if (num % 50 == 0) {
                    storage.put(num);
                    System.out.println(num + ":是50的倍数,被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行.");
        }
    }
}
