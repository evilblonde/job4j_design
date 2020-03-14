package ru.job4j.concurrent;

public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread first = new Thread(ConcurrentOutput::printThreadName);
        Thread second = new Thread(ConcurrentOutput::printThreadName);
        Thread third = new Thread(ConcurrentOutput::printThreadName);
        Thread fourth = new Thread(ConcurrentOutput::printThreadName);
        Thread fifth = new Thread(ConcurrentOutput::printThreadName);
        Thread sixth = new Thread(ConcurrentOutput::printThreadName);
        first.start();
        second.start();
        third.start();
        fourth.start();
        fifth.start();
        sixth.start();
        printThreadName();
    }

    static void printThreadName() {
        System.out.println(Thread.currentThread().getName());
    }

}
