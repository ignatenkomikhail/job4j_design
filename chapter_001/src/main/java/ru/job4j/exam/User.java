package ru.job4j.exam;

import java.util.*;

public class User {
    private String name;
    private TreeSet<String> mails = new TreeSet<>();

    public User(String name, String...str) {
        this.name = name;
        setMails(str);
    }

    public void setMails(String...str) {
        Collections.addAll(mails, str);
    }

    public TreeSet<String> getMails() {
        return mails;
    }

    public boolean contains(TreeSet<String> mails) {
        boolean result = false;
        for (String mail : mails) {
            if (this.mails.contains(mail)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(mails, user.mails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mails);
    }
}
