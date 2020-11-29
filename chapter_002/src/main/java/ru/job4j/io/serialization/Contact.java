package ru.job4j.io.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        System.out.println(contact);
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(tempFile))) {
            oos.writeObject(contact);
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(tempFile))) {
            final Contact contactUnSerial = (Contact) ois.readObject();
            System.out.println(contactUnSerial);
        }
    }
}
