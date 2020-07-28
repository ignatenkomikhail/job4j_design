package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        Node<T> before = null;
        Node<T> after = head.next;
        if (head != null) {
            while (after != null) {
                before = head;
                head = after;
                after = head.next;
                head.next = before;
            }
        }
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> node = head;
            head = head.next;
            node.next = null;
        }
    }

    public T deleteLast() {
        T rslt = null;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            if (head.next == null) {
                rslt = head.value;
                head = null;
            } else {
                Node<T> n = head;
                while (n.next != null) {
                    if (n.next.next == null) {
                        rslt = n.next.value;
                        n.next = null;
                    } else {
                        n = n.next;
                    }
                }
            }
        }
        return rslt;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
