package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddUnsuccessful() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        assertThat(
                tree.add(2, 1),
                is(false)
        );
    }

    @Test
    public void when6ElNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }

    @Test
    public void when6ElBinaryTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }
}
