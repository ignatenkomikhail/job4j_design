package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            consoleOutput(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consoleOutput(StringBuilder sb) {
        String[] str = sb.toString().split(System.lineSeparator());
        for (String s : str) {
            int num = Integer.parseInt(s);
            System.out.println(num + " - "
                    + (((num % 2) == 0) ? "четное" : "нечетное"));
        }
    }
}
