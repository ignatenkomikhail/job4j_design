package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rslt = null;
        try {
            rslt = out.pop();
        } catch (NoSuchElementException nse1) {
            try {
                while (true) {
                    out.push(in.pop());
                }
            } catch (NoSuchElementException nse2) {
                rslt = out.pop();
            }
        }
        return rslt;
    }

    public void push(T value) {
        in.push(value);
    }
}
