package com.panli.concurrent_programming.part02.chap06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lipan
 * @date 2020-09-13
 * @desc 线程不安全问题：初始化和发布导致线程不安全问题
 */
public class WrongInit {
    private Map<Integer, String> students;

    public WrongInit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                students = new HashMap<>();
                students.put(1, "小米");
                students.put(2, "小明");
                students.put(3, "小华");
                students.put(4, "小伟");
            }
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }

    public static void main(String[] args) {
        WrongInit multiThreadsError6 = new WrongInit();
        System.out.println(multiThreadsError6.getStudents().get(1)); // 运行获取并没有等待构造函数初始化完毕
    }
}