package ru.job4j.exam;

import java.util.*;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        HashMap<Integer, String> map = new HashMap<>();
        fillMap(previous, map);
        return getInfo(previous, current, info, map);
    }

    private static Info getInfo(List<User> previous, List<User> current,
                                 Info info, HashMap<Integer, String> map) {
        for (User user : current) {
            if (map.containsKey(user.id)) {
                if (!user.name.equals(map.get(user.id))) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }
        info.deleted = previous.size() + info.added - current.size();
        return info;
    }

    private static void fillMap(List<User> previous,
                                HashMap<Integer, String> map) {
        for (User user : previous) {
            map.put(user.id, user.name);
        }
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            User user = (User) obj;
            return Objects.equals(this.id, user.id);
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;
    }
}
