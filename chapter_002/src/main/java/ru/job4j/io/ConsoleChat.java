package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final HashMap<String, Integer> command = new HashMap<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        command.put(OUT, -1);
        command.put(STOP, 0);
        command.put(CONTINUE, 1);
    }

    public void run() {
        String str;
        int flag = 1;
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        List<String> answers = readAnswers(botAnswers);
        List<String> dialog = new ArrayList<>();
        System.out.println("Числобот готов!");
        do {
            str = sc.nextLine();
            dialog.add(str + System.lineSeparator());
            flag = command.getOrDefault(str, flag);
            if (flag == 1) {
                String answer = answers.get(rnd.nextInt(answers.size()));
                System.out.println(answer);
                dialog.add(answer + System.lineSeparator());
            }
        } while (flag != -1);
        writeDialog(dialog);
    }

    private void writeDialog(List<String> dialog) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            for (String str : dialog) {
                out.write(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readAnswers(String botAnswers) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            in.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./chapter_002/data/dia.log",
                "./chapter_002/data/answers.txt");
        cc.run();
    }
}
