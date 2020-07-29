package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> data = new SimpleArray<>(3);
    private int modCount = 0;
    private int size = 0;

    public void add(T value) {
        if (notIncluded(value)) {
            data.add(value);
            size++;
            modCount++;
        }
    }

    private boolean notIncluded(T value) {
        boolean rslt = true;
        for (T el : data) {
            if (value.equals(el)) {
                rslt = false;
                break;
            }
        }
        return rslt;
    }

    @Override
    public Iterator<T> iterator() {
        return new It();
    }

    private class It implements Iterator<T> {
        private int cursor = 0;
        private int expectedModCount = 0;

        {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data.get(cursor++);
        }
    }
}
