package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

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

    public boolean isStandby() {
        return standby;
    }

    public int getCharge() {
        return charge;
    }

    public String getModel() {
        return model;
    }

    public Service getService() {
        return service;
    }

    public String[] getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        final Robot robot = new Robot(false, 100, "r2d2", new Service(12345, "(555)123-45-67"), "Task1", "Task2");
        final Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(robot);
        System.out.println(jsonString);
        final Robot robot1 = gson.fromJson(jsonString, Robot.class);
        System.out.println(robot1);
        // JSON-Java (org.json)
        JSONObject jsonService = new JSONObject(new Service(12345, "(555)123-45-67"));
        JSONArray jsonTasks = new JSONArray(List.of("Task1", "Task2"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", robot.getModel());
        jsonObject.put("charge", robot.getCharge());
        jsonObject.put("standby", robot.isStandby());
        jsonObject.put("service", jsonService);
        jsonObject.put("tasks", jsonTasks);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(robot).toString());
    }
}
