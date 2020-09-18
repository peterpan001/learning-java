package com.panli.concurrent_programming.part08.chap44;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lipan
 * @date 2020-09-17
 * @desc 10 个线程都要用到 SimpleDateFormat
 */
public class ThreadLocalDemo02 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                String date = new ThreadLocalDemo02().date(finalI);
                System.out.println(date);
            }).start();
            Thread.sleep(100);
        }
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }
}
