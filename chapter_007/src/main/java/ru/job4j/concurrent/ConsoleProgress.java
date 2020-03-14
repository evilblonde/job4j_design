package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    char[] symbols = {'-', '/', '|', '\\'};

    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            Thread.sleep(1000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                printLoading(symbols[i++ % 4]);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printLoading(char c) {
        System.out.print("\rLoading ... " + c);
    }
}
