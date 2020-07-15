package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rslt = false;
        while (row < data.length) {
            if (data[row].length == 0) {
                row++;
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
        Integer rslt = data[row][column];
        if (column < data[row].length - 1) {
            column++;
        } else {
            column = 0;
            do {
                row++;
                if (row == data.length) {
                    break;
                }
            } while (!(data[row].length > 0));
        }
        return rslt;
    }
}
