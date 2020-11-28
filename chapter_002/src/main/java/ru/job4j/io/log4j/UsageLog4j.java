package ru.job4j.io.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = true;
        byte b = 10;
        short s = 10_000;
        char ch = 'a';
        int i = 234;
        long l = 10L;
        float ft = 0.2F;
        double dbl = 0.05D;
        LOG.debug("boolean: {}, byte: {}, short: {}", bool, b, s);
        LOG.debug("char: {}, int: {}, long: {}", ch, i, l);
        LOG.debug("float: {}, double: {}", ft, dbl);
    }
}
