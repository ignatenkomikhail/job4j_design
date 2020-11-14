package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Duplicate {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:\\projects");
        for (Path path : duplicate(start)) {
            System.out.println(path.toRealPath() + " : "
                                + path.toFile().length() + " байт");
        }
    }

    public static Set<Path> duplicate(Path root) throws IOException {
        DuplicateFinder df = new DuplicateFinder();
        Files.walkFileTree(root, df);
        return df.getDuplicate();
    }
}
