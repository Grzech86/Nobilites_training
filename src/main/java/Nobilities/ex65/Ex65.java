package Nobilities.ex65;

import org.w3c.dom.ls.LSOutput;

import java.io.*;

//# Huffman Compression
//        Create stream that code stream using simplified Huffman Compression
//        - a given byte
//        - count of recurrence
//        Test by decompressing output file.
public class Ex65 {
    public static void main(String[] args) throws IOException {
        final var string = "aaaaannna";
        final var bytes = string.getBytes();

        try (final var compressed = new ByteArrayOutputStream()) {

            try (final var bais = new ByteArrayInputStream(bytes);
                 final var cos = new CompressOutputStream(compressed)) {
                bais.transferTo(cos);
            }
            final var compressedBytes = compressed.toByteArray();

            final var bais = new ByteArrayInputStream(compressedBytes);
            final var decompress = new DecompressInputStream(bais);
            final var decompressed = new ByteArrayOutputStream();

            decompress.transferTo(decompressed);

            final var decompressedBytes = decompressed.toByteArray();
        }
    }

}

class DecompressInputStream extends InputStream {
    private final InputStream inputStream;

    public DecompressInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private int occurrences = 0;
    private int previous = 0;

    @Override
    public int read() throws IOException {
        if (occurrences == 0) {
            previous = inputStream.read();
            occurrences = inputStream.read();
        }

        occurrences--;
        return previous;
    }
}

class CompressOutputStream extends OutputStream {
    private final OutputStream outputStream;
    private byte counter = 0;
    private int previous = -1;

    public CompressOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        if (previous == -1 || (b == previous && counter < 100)) {
            counter++;
        } else {
            writeOccurrences();
            counter = 1;
        }

        previous = b;
    }

    @Override
    public void close() throws IOException {
        writeOccurrences();

        super.close();
    }

    private void writeOccurrences() throws IOException {
        outputStream.write(previous);
        outputStream.write(counter);
    }
}