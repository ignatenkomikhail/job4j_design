package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int...data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rslt = false;
        while (point < data.length) {
            if (data[point] % 2 != 0) {
                point++;
            } else {
                rslt = true;
                break;
            }
        }
        return rslt;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
