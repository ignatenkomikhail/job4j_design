package ru.job4j.exam;

import java.util.*;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        HashMap<Integer, String> map = new HashMap<>();
        fillMap(previous, map);
        modMap(previous, current, map);
        return getInfo(map, info);
    }

    private static Info getInfo(HashMap<Integer, String> map, Info info) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            switch (entry.getValue()) {
                case "changed":
                    info.changed++;
                    break;
                case "deleted":
                    info.deleted++;
                    break;
                case "added":
                    info.added++;
                    break;
                default: break;
            }
        }
        return info;
    }

    private static void modMap(List<User> previous, List<User> current, HashMap<Integer, String> map) {
        for (User user : current) {
            if (!map.containsKey(user.id)) {
                map.put(user.id, "added");
            } else {
                if (!user.name.equals(map.get(user.id))) {
                    map.put(user.id, "changed");
                }
            }
        }
        for (User user : previous) {
            if (findById(current, user.id) == null) {
                map.put(user.id, "deleted");
            }
        }
    }

    private static void fillMap(List<User> previous, HashMap<Integer, String> map) {
        for (User user : previous) {
            map.put(user.id, user.name);
        }
    }

    private static User findById(List<User> c, int id) {
        User user = null;
        for (User u : c) {
            if (u.id == id) {
                user = u;
                break;
            }
        }
        return user;
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
