package ru.job4j.sync.collection;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private Object[] dataArray;
    private int size;

    public SingleLockList() {
        this(8);
    }

    public SingleLockList(int initialSize) {
        dataArray = new Object[initialSize];
    }

    public synchronized void add(T value) {
        if (size == dataArray.length) {
            dataArray = Arrays.copyOf(dataArray, dataArray.length + 1);
        }
        dataArray[size++] = value;
    }

    public synchronized T get(int index) {
        if (index > dataArray.length) {
            return null;
        }
        return (T) dataArray[index];
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return new Iterator<>() {
            final Object[] iteratorArray = Arrays.copyOf(dataArray, size);
            final int iteratorSize = size;
            int currentElement = 0;

            @Override
            public boolean hasNext() {
                return currentElement < iteratorSize;
            }

            @Override
            public T next() {
                return (T) iteratorArray[currentElement++];
            }
        };
    }
}
