package com.panli.concurrent_programming.part01.chap04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lipan
 * @date 2020-09-11
 */
public class UnSafeBlockingQueue {
    Queue<String> buffer = new LinkedList<String>();

    public void give(String data){
        buffer.add(data);
        notify();
    }

    public String take() throws InterruptedException {
        while (buffer.isEmpty()){
            wait();
        }
        return buffer.remove();
    }
}
