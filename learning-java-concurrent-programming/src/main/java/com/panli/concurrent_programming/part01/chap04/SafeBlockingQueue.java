package com.panli.concurrent_programming.part01.chap04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lipan
 * @date 2020-09-11
 */
public class SafeBlockingQueue {

    Queue<String> buffer = new LinkedList<String>();

    public void give(String data) {
        synchronized (this) {
            buffer.add(data);
            notify();
        }
    }

    public String take() throws InterruptedException {
        synchronized (this) {
            while (buffer.isEmpty()) {
                wait();
            }
            return buffer.remove();
        }
    }
}
