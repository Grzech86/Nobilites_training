package Nobilities.ex54;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex54 {

    public static void main(String[] args) {
//        Files.createFile(Path.of("outputfile.txt"));
        try (final var is = Files.newBufferedReader(Path.of("outputfile.txt"));
             final var os = Files.newBufferedWriter(Path.of("outputfilenew.txt"));) {
            for(;;) {
                final var line = is.readLine();
                if (line == null) {
                    break;
                }

                final var x = Integer.valueOf(line) + 1;
                os.write(String.valueOf(x));
                os.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class Ex54V2 {

    public static void main(String[] args) {
        //        Files.createFile(Path.of("outputfile.txt"));
        try (final var os = Files.newBufferedWriter(Path.of("outputfilenew.txt"));
             final var stream = Files.lines(Path.of("outputfile.txt"))) {
            stream
                    .map(Integer::valueOf)
                    .map(v -> v + 1)
                    .map(String::valueOf)
                    .map(v -> v + System.lineSeparator())
                    .forEach(line -> {
                        try {
                            os.write(line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}