package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MemStoreTest {

    @Test
    public void whenReplace() {
        UserStore us = new UserStore();
        User user1 = new User("12345");
        User user2 = new User("67890");
        us.add(user1);
        us.replace("12345", user2);
        assertThat(us.findById("67890"), is(user2));
    }

    @Test
    public void whenDelete() {
        RoleStore rs = new RoleStore();
        Role role = new Role("12345");
        rs.add(role);
        rs.delete("12345");
        assertThat(rs.findById("12345"), nullValue());
    }

    @Test
    public void whenFindById() {
        UserStore us = new UserStore();
        RoleStore rs = new RoleStore();
        User user = new User("12345");
        Role role = new Role("67890");
        us.add(user);
        rs.add(role);
        assertThat(us.findById("12345"), is(user));
        assertThat(rs.findById("67890"), is(role));
    }
}
