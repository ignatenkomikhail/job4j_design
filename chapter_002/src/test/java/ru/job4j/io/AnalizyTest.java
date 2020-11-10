package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenUnavailable() {
        String source = "./data/server.log";
        String target = "./data/target.log";
        Analizy a = new Analizy();
        a.unavailable(source, target);
        assertThat(a.loadTarget(target).get(0), is("10:58:01;10:59:01"));
        assertThat(a.loadTarget(target).get(1), is("11:01:02;11:02:02"));
    }

    @Test
    public void whenUnavailableWithTemporaryFolder() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:56:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
        }
        Analizy a = new Analizy();
        a.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String s;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            s = in.readLine();
        }
        assertThat(s, is("10:58:01;10:59:01"));
    }
}
