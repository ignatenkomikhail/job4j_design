package ru.job4j.map;

import java.util.Objects;

public class Animal {
    private String name;
    private int weight;
    private String age;

    public Animal(String name, int weight, String age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return weight == animal.weight
                && Objects.equals(name, animal.name)
                && Objects.equals(age, animal.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(weight);
        result = 31 * result + age.hashCode();
        return result;
    }
}
