package Nobilities.ex61;
import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

//# Walker
//        Create an application printing tree of folders starting from a given one.
//        (aka unix tree command)
public class Ex61 {

    public static final void analyze(Path root, String prefix, List<String> lines) throws IOException {
        final var paths = Files.newDirectoryStream(root);
        for (final var path : paths) {
            final var file = path.toFile();
            if (file.isDirectory() && file.getName() != "." && file.getName() != "..") {
                analyze(path, prefix + "  ", lines);
            }

            final var value = prefix + file.getName() +
                    ", Last modified: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneOffset.systemDefault()) +
                    ", Size: " + file.length();

            lines.add(0, value);
        }
    }

    public static void main(String[] args) throws IOException {
        final var lines = new LinkedList<String>();
        analyze(Path.of("."), "", lines);

        lines.forEach(System.out::println);
    }
}
class WalkFileTreeExample {


    public static void main(String... args) throws IOException {
        Files.walkFileTree(Paths.get("."), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("-" + dir);
                return CONTINUE;
            }
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("--" + file);
                return CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                return CONTINUE;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                return CONTINUE;
            }
        });


    }
}

//class FileSearchExample implements FileVisitor<Path> {
//
//    public static void main(String[] args) throws IOException {
//        Path startingDir = Paths.get(".");
//
//        FileSearchExample crawler = new FileSearchExample(startingDir);
//        Files.walkFileTree(startingDir, crawler);
//    }
//
//    @Override
//    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
//        return CONTINUE;
//    }
//    // standard constructors
//    @Override
//    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//        String fileName = file.getFileName().toString();
//        if (fileName.equals(fileName)) {
//            System.out.println("File found: " + file.toString());
//            return TERMINATE;
//        }
//        return CONTINUE;
//    }
//    @Override
//    public FileVisitResult visitFileFailed(Path file, IOException exc) {
//        System.out.println("Failed to access file: " + file.toString());
//        return CONTINUE;
//    }
//    @Override
//    public FileVisitResult postVisitDirectory(Path dir, IOException exc){
//        boolean finishedSearch = Files.isSameFile(dir, startingDir);
//        if (finishedSearch) {
//            System.out.println("File:" + fileName + " not found");
//            return TERMINATE;
//        }
//        return CONTINUE;
//    }
//
//}