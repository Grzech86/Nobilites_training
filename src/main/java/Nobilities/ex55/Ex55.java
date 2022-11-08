package Nobilities.ex55;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Create an application that creates a duplicate of existing file.
public class Ex55 {

    public static void main(String[] args) throws IOException {
//        Files.createFile(Path.of("outputfile.txt"));
        Files.copy(Path.of("outputfile.txt"),Path.of("outputfile2.txt"));

}
}
