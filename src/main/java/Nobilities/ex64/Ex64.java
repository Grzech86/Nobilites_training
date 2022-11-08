package Nobilities.ex64;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

//# Caesar cipher
//        Create a stream that encrypts input stream from text file using [Caesar cipher]
//        (https://en.wikipedia.org/wiki/Caesar_cipher) and writes result to output file.
//        Confirm correctness by decrypting encrypted file.
public class Ex64 {


    // Encrypts text using a shift od s
    public static StringBuffer encrypt(String text, int s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        s - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) +
                        s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }

    // Driver code
    public static void main(String[] args) {

        int s = 4;

        try (
                final var streamInput = Files.lines(Path.of("inputCaesarCipher.txt"));
                final var osEncrypt = Files.newBufferedWriter(Path.of("encryptCaesarCipher.txt"));
        ) {
            streamInput
                    .map(v -> encrypt(v, s))
                    .forEach(line -> {
                        try {
                            osEncrypt.write(String.valueOf(line));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (
                final var streamOutput = Files.lines(Path.of("encryptCaesarCipher.txt"));
                final var osDecrypt = Files.newBufferedWriter(Path.of("decryptCaesarCipher.txt"));
        ) {
            streamOutput
                    .map(v -> encrypt(v, 26 - s))
                    .forEach(line -> {
                        try {
                            osDecrypt.write(String.valueOf(line));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
class CaesarOS {
    public static void main(String[] args) throws IOException {
        final var input = "ala ma kota";
        final var byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        final var outputStream = Files.newOutputStream(Path.of("encrypted.txt"));
        final var caesarOutputStream = new CaesarOutputStream(outputStream);

        byteArrayInputStream.transferTo(caesarOutputStream);

        final var inputStream = Files.newInputStream(Path.of("encrypted.txt"));

        CaesarInputStream caesarInputStream = new CaesarInputStream(inputStream);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        caesarInputStream.transferTo(byteArrayOutputStream);

        String decrypted = byteArrayOutputStream.toString();
        System.out.println(decrypted);
    }
}
class CaesarInputStream extends InputStream {

    private  final InputStream inputStream;

    public CaesarInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    @Override
    public int read() throws IOException {
        int read = inputStream.read();
        if (read == -1) {
            return -1;
        }
        return encryption(read, -13);
    }

    public static int encryption(int original, int transposition) {
        int indexOfA = 65;
        int indexOfZ = 90;
        int indexOfa = 97;
        int indexOfz = 122;
        int encryptedIndex;
        int localTransposition = transposition % 26;

        if (original >= indexOfA && original <= indexOfZ) {
            if (original + localTransposition < indexOfA) {
                encryptedIndex = original + localTransposition + 26;
            } else {
                encryptedIndex = indexOfA + (original - indexOfA + localTransposition) % 26;
            }
        } else if (original >= indexOfa && original <= indexOfz) {
            if (original + localTransposition < indexOfa) {
                encryptedIndex = original + localTransposition + 26;
            } else {
                encryptedIndex = indexOfa + (original - indexOfa + localTransposition) % 26;
            }
        } else {
            encryptedIndex = original;
        }
        return encryptedIndex;
    }
}

class CaesarOutputStream extends OutputStream {
    private final OutputStream outputStream;

    public CaesarOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        int encryption = encryption(b, 13);
        outputStream.write(encryption);
    }

    public static int encryption(int original, int transposition) {
        int indexOfA = 65;
        int indexOfZ = 90;
        int indexOfa = 97;
        int indexOfz = 122;
        int encryptedIndex;
        int localTransposition = transposition % 26;

        if (original >= indexOfA && original <= indexOfZ) {
            if (original + localTransposition < indexOfA) {
                encryptedIndex = original + localTransposition + 26;
            } else {
                encryptedIndex = indexOfA + (original - indexOfA + localTransposition) % 26;
            }
        } else if (original >= indexOfa && original <= indexOfz) {
            if (original + localTransposition < indexOfa) {
                encryptedIndex = original + localTransposition + 26;
            } else {
                encryptedIndex = indexOfa + (original - indexOfa + localTransposition) % 26;
            }
        } else {
            encryptedIndex = original;
        }
        return encryptedIndex;
    }
}