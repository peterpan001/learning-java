package com.panli.concurrent_programming.part09.chap48;

import java.io.IOException;

/**
 * @author lipan
 * @date 2020-09-17
 * @desc Runnable不能抛出异常
 */
public class RunThrowException {


    public void normalMethod() throws IOException {
        throw new IOException();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
