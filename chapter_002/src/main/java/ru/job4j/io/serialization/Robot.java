package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Robot {
    private final boolean standby;
    private final int charge;
    private final String model;
    private final Service service;
    private final String[] tasks;

    public Robot(boolean standby, int charge, String model, Service service, String...tasks) {
        this.standby = standby;
        this.charge = charge;
        this.model = model;
        this.service = service;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Robot{"
                + "standby=" + standby
                + ", charge=" + charge
                + ", model='" + model + '\''
                + ", service=" + service
                + ", tasks=" + Arrays.toString(tasks)
                + '}';
    }

    public static void main(String[] args) {
        final Robot robot = new Robot(false, 100, "r2d2", new Service(12345, "(555)123-45-67"), "Task1", "Task2");
        final Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(robot);
        System.out.println(jsonString);
        final Robot robot1 = gson.fromJson(jsonString, Robot.class);
        System.out.println(robot1);
    }
}
