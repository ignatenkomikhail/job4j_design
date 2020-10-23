package ru.job4j.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MailTest {

    @Test
    public void whenCompact() {
        User u1 = new User("User1", "xxx@ya.ru", "foo@gmail.com", "lol@mail.ru");
        User u2 = new User("User2", "foo@gmail.com", "ups@pisem.net");
        User u3 = new User("User3", "xyz@pisem.net", "vasya@pupkin.com");
        User u4 = new User("User4", "ups@pisem.net", "aaa@bbb.ru");
        User u5 = new User("User5", "xyz@pisem.net");
        Mail mail = new Mail(u1, u2, u3, u4, u5);
        ArrayList<User> expected = new ArrayList<>(List.of(
                new User("User1", "aaa@bbb.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "xxx@ya.ru"),
                new User("User3", "vasya@pupkin.com", "xyz@pisem.net")));
        assertThat(mail.compact(), is(expected));
    }

    @Test
    public void whenCompact2() {
        User u1 = new User("User1", "xxx@ya.ru", "foo@gmail.com", "lol@mail.ru");
        User u2 = new User("User2", "xyz@pisem.net");
        Mail mail = new Mail(u1, u2);
        ArrayList<User> expected = new ArrayList<>(List.of(
                new User("User1", "foo@gmail.com", "lol@mail.ru", "xxx@ya.ru"),
                new User("User2", "xyz@pisem.net")));
        assertThat(mail.compact(), is(expected));
    }
}