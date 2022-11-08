package Nobilities.ex60;
//# List Directory
//        Implement an application listing all files with all details

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

public class Ex60 {
    public static void main(String[] args) throws IOException, InterruptedException {


            DirectoryStream<Path> paths = Files.newDirectoryStream(Path.of("copy"));
            for (final var path : paths) {
                File file = path.toFile();
                if (file.isDirectory()) {
                    continue;
                }

                // "../../build"
                // "c:/tmp"
                // "../tmp/../../../ala/kot/ma"

                System.out.println(
                        " File name: " + file.getName()+
                                ", DATA: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneOffset.systemDefault())+
                                ", Size: " + file.length()
                );
            }
        }
    }