package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rslt = false;
        T obj = findById(id);
        if (obj != null) {
            mem.remove(obj);
            mem.add(model);
            rslt = true;
        }
        return rslt;
    }

    @Override
    public boolean delete(String id) {
        boolean rslt = false;
        T obj = findById(id);
        if (obj != null) {
            mem.remove(obj);
            rslt = true;
        }
        return rslt;
    }

    @Override
    public T findById(String id) {
        T rslt = null;
        for (T t : mem) {
            if (id.equals(t.getId())) {
                rslt = t;
                break;
            }
        }
        return rslt;
    }
}
