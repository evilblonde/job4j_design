package ru.job4j.sync;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountTest {

    @Test
    public void whenExecute2ThreadsThenCountIs2() throws InterruptedException {
        final Count count = new Count();

        Thread thr1 = new ThreadCount(count);
        Thread thr2 = new ThreadCount(count);

        thr1.start();
        thr2.start();

        thr1.join();
        thr2.join();

        assertEquals(2, count.get());
    }

    private static class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }
}