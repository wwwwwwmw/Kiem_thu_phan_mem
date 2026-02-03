package org.example;

public class Bai2 {
    private final String name;
    private final int age;

    public Bai2(String name, int age) {
        this.name = name;
        this.age = age;

        // Kiểm tra điều kiện logic
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
    }
}