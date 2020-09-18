package com.panli.concurrent_programming.part06.chap35;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lipan
 * @date 2020-09-15
 */
public class BlockingQueueMethodTest {

    public static void main(String[] args) {
        // testAdd();
        // testRemove();
        // testElement();
        // testOffer();
        // testPoll();
        testPeek();
    }

    private static void testAdd() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
    }

    private static void testRemove() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    private static void testElement() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.element();
    }

    private static void testOffer() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(1));
    }

    private static void testPoll() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void testPeek() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.peek());
    }
}
