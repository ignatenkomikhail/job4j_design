package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table = new Node[2];
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap() {
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (size >= (table.length * LOAD_FACTOR)) {
            resize();
        }
        int hash = hash(key);
        int i = hash & (table.length - 1);
        if (table[i] == null) {
            table[i] = new Node<>(hash, key, value);
            size++;
            modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        V result = null;
        int i = hash(key) & (table.length - 1);
        Node<K, V> t = table[i];
        if (t != null && (Objects.equals(key, t.key))) {
            result = t.value;
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int i = hash(key) & (table.length - 1);
        Node<K, V> t = table[i];
        if (t != null && (Objects.equals(key, t.key))) {
            table[i] = null;
            size--;
            modCount++;
            result = true;

        }
        return result;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    private void resize() {
        Node<K, V>[] newTab = new Node[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            int index = table[i].hash & newTab.length - 1;
            newTab[index] = table[i];
        }
        table = newTab;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            private int value = 0;
            private int expectedModCount = 0;

            {
                expectedModCount = modCount;
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return value < size;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[cursor] == null) {
                    cursor++;
                }
                value++;
                return table[cursor++].value;
            }
        };
    }

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
