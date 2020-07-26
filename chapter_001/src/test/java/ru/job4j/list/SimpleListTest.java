package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleListTest {
    SimpleList<Integer> data;
    Iterator<Integer> it;

    @Before
    public void setUp() {
        data = new SimpleList<>();
        data.add(0);
        data.add(1);
        it = data.iterator();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGet() {
        assertThat(data.get(0), is(0));
        assertThat(data.get(1), is(1));
        data.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterator() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIterator() {
        data.add(2);
        it.next();
    }
}
