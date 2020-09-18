package com.panli.concurrent_programming.part08.chap44;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lipan
 * @date 2020-09-17
 * @desc 2 个线程都要用到 SimpleDateFormat
 */
public class ThreadLocalDemo01 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            String date = new ThreadLocalDemo01().date(1);
            System.out.println(date);
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            String date = new ThreadLocalDemo01().date(2);
            System.out.println(date);
        }).start();
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }
}
