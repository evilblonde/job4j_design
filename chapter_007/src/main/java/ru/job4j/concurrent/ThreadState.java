package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread thread1 = new Thread(ConcurrentOutput::printThreadName);
        Thread thread2 = new Thread(ConcurrentOutput::printThreadName);
        thread1.start();
        thread2.start();
        while (thread1.getState() != Thread.State.TERMINATED && thread2.getState() != Thread.State.TERMINATED) {
            System.out.println("...");
        }
        System.out.println("All threads were stopped!");
    }
}
