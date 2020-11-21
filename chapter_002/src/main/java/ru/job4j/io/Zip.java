package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> search(Path start, String ext) throws IOException {
        ZipFiles zf = new ZipFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(start, zf);
        return zf.getPaths();
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ZipArg za = new ZipArg(args);
        if (za.valid()) {
            Path start = Path.of(za.directory());
            List<File> sources = zip.search(start, za.exclude());
            zip.packFiles(sources, new File(za.output()));
        }
    }
}
