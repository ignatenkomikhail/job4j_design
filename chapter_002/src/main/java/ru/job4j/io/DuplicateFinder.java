package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class DuplicateFinder implements FileVisitor<Path> {
    private HashMap<String, Path> map = new HashMap<>();
    private Set<Path> list = new TreeSet<>();

    public Set<Path> getDuplicate() {
        return list;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String name = file.getFileName().toString();
        if (file.toFile().isFile()) {
            if (!map.containsKey(name)) {
                map.put(name, file);
            } else if (map.get(name).toFile().length() == Files.size(file)) {
                list.add(map.get(name).toRealPath());
                list.add(file.toRealPath());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
