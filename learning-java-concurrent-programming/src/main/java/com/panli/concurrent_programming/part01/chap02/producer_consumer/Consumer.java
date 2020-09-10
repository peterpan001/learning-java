package com.panli.concurrent_programming.part01.chap02.producer_consumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 消费者
 */
public class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.97) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
