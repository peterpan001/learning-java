package com.panli.concurrent_programming.part05.chap33;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lipan
 * @date 2020-09-15
 * @desc CopyOnWriteArrayList 的迭代器一旦被建立之后，如果往之前的 CopyOnWriteArrayList 对象中去新增元素，在迭代器中既不会显示出元素的变更情况，同时也不会报错
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList(new Integer[]{1, 2, 3});
        System.out.println(list);

        // Get iterator 1
        Iterator itr1 = list.iterator();

        // Add one element and verify list is updated
        list.add(4);
        System.out.println(list); // [1,2,3]

        // Get iterator 2
        Iterator itr2 = list.iterator(); // [1,2,3,4]

        System.out.println("====Verify Iterator 1 content====");

        itr1.forEachRemaining(System.out::println); // 1,2,3

        System.out.println("====Verify Iterator 2 content====");

        itr2.forEachRemaining(System.out::println); //1,2,3,4
    }
}
