package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleHashMapTest {
    SimpleHashMap<Integer, String> data;
    Iterator<String> it;

    @Before
    public void setUp() {
        data = new SimpleHashMap<>();
        data.insert(null, "null");
        data.insert(1, "one");
        it = data.iterator();
    }

    @Test
    public void whenGet() {
        assertThat(data.get(null), is("null"));
        assertThat(data.get(1), is("one"));
        assertThat(data.get(2), nullValue());
    }

    @Test
    public void whenDelete() {
        assertThat(data.delete(null), is(true));
        assertThat(data.delete(1), is(true));
        assertThat(data.get(null), nullValue());
        assertThat(data.get(1), nullValue());
    }

    @Test
    public void whenResize() {
        data.insert(2, "two");
        assertThat(data.get(2), is("two"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterator() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("null"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIterator() {
        data.insert(2, "two");
        it.next();
    }
}