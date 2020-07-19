package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleArrayTest {
    private SimpleArray<Integer> data;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        data = new SimpleArray<>(2);
        data.add(0);
        data.add(1);
        it = data.iterator();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddArrayIsFull() {
        data.add(2);
    }

    @Test
    public void whenAddData() {
        assertThat(data.get(0), is(0));
        assertThat(data.get(1), is(1));
    }

    @Test
    public void whenSetData() {
        data.set(0, 9);
        assertThat(data.get(0), is(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveData() {
        data.remove(1);
        data.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateData() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
