package Nobilities.ex56;
//Create an application that create a compressed file of an existing one. Use zip compression method.

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Ex56 {

    public static void main(String[] args) throws IOException {
        try (final var os = new FileOutputStream("outputfile.zip");
             final var zipOutputStream = new ZipOutputStream(os)) {
            zipOutputStream.putNextEntry(new ZipEntry("inputnowyfilep.litkxt"));
            Files.copy(Path.of("inputnowyfilep.litkxt"), zipOutputStream);
        }
    }
}