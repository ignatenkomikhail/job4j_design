package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {

    public static void matrixOutput(int size) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    String s = String.format("%4d", i * j);
                    out.write(s.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Matrix.matrixOutput(10);
    }
}
