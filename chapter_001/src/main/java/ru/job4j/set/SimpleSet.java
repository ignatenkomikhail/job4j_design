package ru.job4j.set;

import ru.job4j.generic.SimpleArray;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> data = new SimpleArray<>(3);

    public void add(T value) {
        if (notIncluded(value)) {
            data.add(value);
        }
    }

    private boolean notIncluded(T value) {
        boolean rslt = true;
        for (T el : data) {
            if (Objects.equals(value, el)) {
                rslt = false;
                break;
            }
        }
        return rslt;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
