package com.panli.concurrent_programming.part05.chap29;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author lipan
 * @date 2020-09-14
 * @desc Hash不安全demo：扩容期间，取值不准确
 */
public class HashMapNotSafe {
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        final Integer targetKey = 0b1111_1111_1111_1111; // 65 535
        final String targetValue = "v";
        map.put(targetKey, targetValue);

        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println(map.get(targetKey));
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }
}
