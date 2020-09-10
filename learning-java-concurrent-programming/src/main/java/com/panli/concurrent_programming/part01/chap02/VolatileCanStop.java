package com.panli.concurrent_programming.part01.chap02;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 使用 volatile 停止线程
 */
public class VolatileCanStop implements Runnable {

    private volatile boolean canneled = false;


    @Override
    public void run() {
        int num = 0;
        try {
            while (!canneled && num < 1000000) {
                if (num % 10 == 0) {
                    System.out.println(num + "是10的倍数.");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileCanStop r = new VolatileCanStop();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(3000);
        r.canneled = true;
    }
}
