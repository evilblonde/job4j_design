package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 101; i++) {
                printLoadingState(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printLoadingState(int percent) {
        System.out.print("\rLoading : " + percent + "%");
    }
}
