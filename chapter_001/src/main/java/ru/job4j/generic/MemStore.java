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
        if (this.delete(id)) {
            mem.add(model);
            rslt = true;
        }
        return rslt;
    }

    @Override
    public boolean delete(String id) {
        boolean rslt = false;
        int index = this.indexOf(id);
        if (index != -1) {
            mem.remove(index);
            rslt = true;
        }
        return rslt;
    }

    @Override
    public T findById(String id) {
        int index = this.indexOf(id);
        return (index == -1) ? null : this.mem.get(index);
    }

    private int indexOf(String id) {
        int rslt = -1;
        for (int i = 0; i < this.mem.size(); i++) {
            if (this.mem.get(i).getId().equals(id)) {
                rslt = i;
                break;
            }
        }
        return rslt;
    }
}
