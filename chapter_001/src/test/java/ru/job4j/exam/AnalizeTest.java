package ru.job4j.exam;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AnalizeTest {

    @Test
    public void when2Added() {
        Analize.User u1 = new Analize.User(1, "one");
        Analize.User u2 = new Analize.User(2, "two");
        Analize.User u3 = new Analize.User(3, "three");
        List<Analize.User> previous = List.of(u1);
        List<Analize.User> current = List.of(u1, u2, u3);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void when2Changed() {
        Analize.User u1 = new Analize.User(1, "one");
        Analize.User u2 = new Analize.User(2, "two");
        Analize.User u3 = new Analize.User(1, "one_changed");
        Analize.User u4 = new Analize.User(2, "two_changed");
        List<Analize.User> previous = List.of(u1, u2);
        List<Analize.User> current = List.of(u3, u4);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void when2Deleted() {
        Analize.User u1 = new Analize.User(1, "one");
        Analize.User u2 = new Analize.User(2, "two");
        Analize.User u3 = new Analize.User(3, "three");
        Analize.User u4 = new Analize.User(4, "four");
        List<Analize.User> previous = List.of(u1, u2, u3, u4);
        List<Analize.User> current = List.of(u1, u3);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenUnmodified() {
        Analize.User u1 = new Analize.User(1, "one");
        Analize.User u2 = new Analize.User(2, "two");
        List<Analize.User> previous = List.of(u1, u2);
        List<Analize.User> current = List.of(u1, u2);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }
}