package Nobilities.ex53;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ex53 {

    public static void main(String[] args) {
//        Files.createFile(Path.of("outputfile.txt"));
        try(final var os = Files.newBufferedWriter(Path.of("outputfile.txt"))) {
            for (int i = 1; i <= 1000 ; i++) {
                os.write(String.valueOf(i));
                os.newLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
