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

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String str;
        int flag = 1;
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        List<String> answers = readAnswers(botAnswers);
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        System.out.println("Числобот готов!");
        do {
            str = sc.nextLine();
            sj.add(str);
            switch (str) {
                case STOP:
                    flag = 0;
                    break;
                case CONTINUE:
                    flag = 1;
                    break;
                case OUT:
                    flag = -1;
                    break;
                default:
                    break;
            }
            if (flag == 1) {
                String answer = answers.get(rnd.nextInt(answers.size()));
                System.out.println(answer);
                sj.add(answer);
            }
        } while (flag != -1);
        writeDialog(sj);
    }

    private void writeDialog(StringJoiner sj) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            out.write(sj.toString());
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
