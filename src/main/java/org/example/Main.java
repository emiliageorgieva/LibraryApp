package org.example;

public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            if (i >= 54 && i <= 74) {
                continue;
            }
            System.out.println(i);
        }
    }
}