package ru.job4j.io.serialization;

public class Service {
    private final int id;
    private final String contact;

    public Service(int id, String contact) {
        this.id = id;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Service{"
                + "id=" + id
                + ", contact='" + contact + '\''
                + '}';
    }

    public int getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }
}
