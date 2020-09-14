package com.panli.concurrent_programming.part04.chap28;

/**
 * @author lipan
 * @date 2020-09-14
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Person p) {
        this(p.getName(), p.getAge());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Employee {
    private Person person;

    public Person getPerson() {
        return new Person(person);
    }

    public void printEmployeeDetail(Employee emp) {
        Person person = emp.getPerson();
        // this caller does not modify the object, so defensive copy was unnecessary
        System.out.println("Employee's name: " + person.getName() + "; age: " + person.getAge());

    }
}
