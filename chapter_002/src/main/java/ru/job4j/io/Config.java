package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().filter(this::isSetting)
                      .forEach(this::addSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addSetting(String s) {
        String[] str = s.split("=");
        values.put(str[0], str[1]);
    }

    private boolean isSetting(String e) {
        boolean result = true;
        if (e.equals("") || e.contains("##")) {
            result = false;
        }
        return result;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/data/app.properties"));
    }
}
