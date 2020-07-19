package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int index = 0;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }

    public void add(T value) {
        if (index == data.length) {
            throw new IndexOutOfBoundsException("Array is full!");
        }
        this.data[index++] = value;
    }

    public void set(int index, T value) {
        Objects.checkIndex(index, this.index);
        this.data[index] = value;
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
    }

    @Override
    public Iterator<T> iterator() {
        return new It();
    }

    private class It implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
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
