package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> node = findBy(parent);
            if (node.isPresent()) {
                node.get().children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        return testBinary(root.children);
    }

    private boolean testBinary(List<Node<E>> list) {
        boolean rsl = false;
        if (list.size() <= 2) {
            rsl = true;
            for (Node<E> node : list) {
                if (!testBinary(node.children)) {
                    rsl = false;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
