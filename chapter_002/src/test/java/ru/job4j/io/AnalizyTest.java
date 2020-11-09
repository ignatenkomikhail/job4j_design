package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void whenUnavailable() {
        String source = "./data/server.log";
        String target = "./data/target.log";
        Analizy a = new Analizy();
        a.unavailable(source, target);
        assertThat(a.loadTarget(target).get(0), is("10:58:01;10:59:01"));
        assertThat(a.loadTarget(target).get(1), is("11:01:02;11:02:02"));
    }
}
