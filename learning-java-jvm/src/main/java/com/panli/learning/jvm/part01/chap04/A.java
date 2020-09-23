package com.panli.learning.jvm.part01.chap04;

/**
 * @author lipan
 * @date 2020-09-22
 */
public class A {

    private B b = new B();

    public static void main(String[] args) {
        A a = new A();
        long num = 4321;
        long ret = a.b.test(num);
        System.out.println(ret);
    }
}

class B {
    private int a = 1234;
    static long c = 111;

    public long test(long num) {
        long ret = this.a + num + c;
        return ret;
    }
}