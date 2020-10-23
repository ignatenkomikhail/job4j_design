package ru.job4j.exam;

import java.util.ArrayList;
import java.util.Collections;

public class Mail {
    private ArrayList<User> data = new ArrayList<>();

    public Mail(User...users) {
        Collections.addAll(data, users);
    }

    public ArrayList<User> compact() {
        int i = 0;
        while (i < data.size()) {
            unification(data.get(i), i++);
        }
        return data;
    }

    private void unification(User user, int index) {
        for (int i = ++index; i < data.size(); i++) {
            if (user.contains(data.get(i).getMails())) {
                user.getMails().addAll(data.get(i).getMails());
                data.remove(i--);
            }
        }
    }
}
