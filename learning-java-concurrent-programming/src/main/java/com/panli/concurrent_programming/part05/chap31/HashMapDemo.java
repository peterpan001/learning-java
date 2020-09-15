package com.panli.concurrent_programming.part05.chap31;

import java.util.HashMap;

/**
 * @author lipan
 * @date 2020-09-14
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap map = new HashMap<HashMapDemo, Integer>(1);
        for (int i = 0; i < 1000; i++){
            HashMapDemo demo1 = new HashMapDemo();
            map.put(demo1, null);
        }
        System.out.println("运行结束");
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
