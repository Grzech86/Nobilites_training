package Nobilities.ex59;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//# FileWatcher
// Implement application monitoring a given folder and copying new file to other destination.

public class FileWatcher {
    public static void main(String[] args) throws IOException, InterruptedException {

        final var lengths = new HashMap<Path, Long>();
        for (; ; ) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(Path.of("watched"));
            for (final var path : paths) {
                File file = path.toFile();
                if (file.isDirectory()) {
                    continue;
                }

                final var current = file.length();
                final var previous = lengths.getOrDefault(path, 0L);
                lengths.put(path, current);

                if (current != previous) {
                    continue;
                }

                final var target = Path.of("copy/" + file.getName());
                if (target.toFile().exists() && target.toFile().length() == current) {
                    continue;
                }

                Files.copy(path, target);
            }

            TimeUnit.SECONDS.sleep(10);
        }

        //
        // 1. newfile.txt created, lenght() = 0
        // 2. newfile.txt - something appended new data, length() = 1GB
        // 3. newfile.txt - something appended new data, length() = 11GB
        // 4. newfile.txt - something appended new data, , length() = 111GB
        // 5. newfile.txt - completed, length() = 111GB
    }
}
