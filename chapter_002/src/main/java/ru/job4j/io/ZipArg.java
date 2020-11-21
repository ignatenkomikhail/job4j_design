package ru.job4j.io;

import java.util.HashMap;

public class ZipArg {
    private final String[] args;
    private HashMap<String, String> values = new HashMap<>();

    public ZipArg(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = true;
        if (args.length != 3) {
            result = false;
        } else {
            for (String arg : args) {
                String[] param = arg.split("=");
                if (param.length != 2) {
                    result = false;
                    break;
                } else {
                    values.put(param[0].substring(1), param[1]);
                }
            }
        }
        return result;
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }
}
