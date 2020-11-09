package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    private int unavailable = 0;
    private StringJoiner sj = new StringJoiner("");

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines().forEach(this::prepare);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.print(sj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepare(String s) {
        String[] str = s.split(" ");
        switch (str[0]) {
            case "200":
            case "300":
                if (unavailable == 1) {
                    unavailable = 0;
                    sj.add(str[1] + System.lineSeparator());
                }
                break;
            case "400":
            case "500":
                unavailable = 1;
                sj.add(str[1] + ";");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + str[0]);
        }
    }

    public ArrayList<String> loadTarget(String target) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
