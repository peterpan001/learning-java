package com.panli.concurrent_programming.part01.chap01.source_code;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 借助线程池创建多线程；本例子是线程池源码：实现线程工厂创建多线程，但其本质还是借助Thread类创建多线程
 *
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static String namePrefix = null;
    private ThreadGroup threadGroup = null;
    private AtomicInteger pollNumber = new AtomicInteger();
    private AtomicInteger threadNumber = new AtomicInteger();

    DefaultThreadFactory() {
        SecurityManager securityManager = System.getSecurityManager();
        ThreadGroup threadGroup = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" + pollNumber.getAndIncrement() + "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
