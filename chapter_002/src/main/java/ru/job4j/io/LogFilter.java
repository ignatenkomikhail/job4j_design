package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().filter(LogFilter::contains404).forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static boolean contains404(String s) {
        boolean result = false;
        String[] str = s.split(" ");
        if (str[str.length - 2].equals("404")) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}
