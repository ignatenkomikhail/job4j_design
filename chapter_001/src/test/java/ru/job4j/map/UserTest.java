package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void userTest1() {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("one", 1, calendar);
        User user2 = new User("one", 1, calendar);
        User user3 = new User("one", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "1");
        map.put(user2, "2");
        map.put(user3, "3");
        System.out.println(map);
    }
}
