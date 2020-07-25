package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int index = 0;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }

    public void add(T value) {
        if (this.index == this.data.length) {
            increase();
        }
        this.data[index++] = value;
        this.modCount++;
    }

    public void set(int index, T value) {
        Objects.checkIndex(index, this.index);
        this.data[index] = value;
        this.modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) this.data[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, this.index);
        System.arraycopy(this.data, index + 1,
                            this.data, index,
                            this.data.length - 1 - index);
        this.index--;
        this.modCount++;
    }

    private void increase() {
        int capacity = (this.data.length * 3) / 2 + 1;
        Object[] newData = new Object[capacity];
        System.arraycopy(this.data, 0, newData, 0, this.index);
        this.data = newData;
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
            return cursor < index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[cursor++];
        }
    }
}
