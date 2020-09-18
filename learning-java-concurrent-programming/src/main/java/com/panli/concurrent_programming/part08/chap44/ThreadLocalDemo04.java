package com.panli.concurrent_programming.part08.chap44;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipan
 * @date 2020-09-17
 * @Desc 所有的线程都共用一个 simpleDateFormat 对象
 * 线程不安全，出现了并发安全问题
 */
public class ThreadLocalDemo04 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    public String date(int seconds) {
        Date date = new Date(seconds * 1000);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalDemo04().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
}
