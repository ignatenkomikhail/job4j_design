package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleList<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> first;
    private Node<T> last;

    public void add(T value) {
        Node<T> last = this.last;
        Node<T> node = new Node<>(value, last, null);
        this.last = node;
        if (last == null) {
            this.first = node;
        } else {
            last.next = node;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> n;
        if (index < (size / 2)) {
            n = first;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
        } else {
            n = last;
            for (int i = size - 1; i > index; i--) {
                n = n.next;
            }
        }
        return n.node;
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
            return get(cursor++);
        }
    }

    private static class Node<T> {
        T node;
        Node<T> prev;
        Node<T> next;

        public Node(T node, Node<T> prev, Node<T> next) {
            this.node = node;
            this.prev = prev;
            this.next = next;
        }
    }
}
