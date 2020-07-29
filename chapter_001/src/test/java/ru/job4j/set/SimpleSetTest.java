package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddData() {
        SimpleSet<String> data = new SimpleSet<>();
        data.add("0");
        data.add("0");
        Iterator<String> it = data.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("0"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateData() {
        SimpleSet<String> data = new SimpleSet<>();
        data.add("0");
        data.add("1");
        Iterator<String> it = data.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("0"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("1"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> data = new SimpleSet<>();
        Iterator<String> it;
        data.add("0");
        it = data.iterator();
        data.add("1");
        it.next();
    }
}