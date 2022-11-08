package Nobilities.ex66;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//# Streamed Non-zero Analysis
//        Implement an adapter for InputStream interface that converts byte
//        stream into a stream of strings with an information about
//        non zeros areas in the form: offset, length.
//        Note: use test datafile from [Non-zero Analysis].
public class Ex66 {


    public static void main(String[] args) {

//        try (final var adaptedOutputStream = new ByteArrayOutputStream() )  {
//
//            try (final var is = new BufferedInputStream(Files.newInputStream(Path.of("bigfile.dat")));
//                 final var os = new BufferedOutputStream(new AdaptOutputStream(adaptedOutputStream))) {
//                is.transferTo(os);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try (final var is = new BufferedInputStream(Files.newInputStream(Path.of("bigfile.dat")))) {
            final var nza = new NonZeroAdapter(is);

            for (; ; ) {
                final var line = nza.next();
                if (line == null) {
                    break;
                }

                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class NonZeroAdapter {
    private final InputStream is;
    private State state = State.NONZERO;

    private long currentOffset = 0;
    private long offset = 0;

    public NonZeroAdapter(InputStream is) {
        this.is = is;
    }

    public String next() {
        try {
            for (; ; ) {
                final var byteAsInt = is.read();
                final var readByte = (byte) byteAsInt;

                switch (state) {
                    case NONZERO:
                        if (byteAsInt == -1 || readByte == 0) {
                            state = State.ZERO;
                            final var length = offset - currentOffset;

                            final var stringWithInformationOfNonZeroArea = "Offset: " + currentOffset + " length: " + length + System.lineSeparator();
                            return stringWithInformationOfNonZeroArea;
                        }
                        if (byteAsInt == -1) {
                            return null;
                        }
                        break;

                    case ZERO:
                        if (byteAsInt == -1) {
                            return null;
                        }

                        if (readByte != 0) {
                            state = State.NONZERO;
                            currentOffset = offset;
                        }
                        break;
                }
                offset++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class AdaptOutputStream extends OutputStream {
    private final OutputStream outputStream;                    // lokalny strumień output

    public AdaptOutputStream(OutputStream os) {                 // konstruktor
        this.outputStream = os;
    }

    long currentOffset = 0;
    long offset = 0;
    State state = State.NONZERO;

    @Override

    public void write(int byteAsInt) throws IOException {
        outer:
        for (; ; ) {
            System.out.println(byteAsInt);
            final var readByte = (byte) byteAsInt;

            switch (state) {
                case NONZERO:
                    if (byteAsInt == -1 || readByte == 0) {
                        state = State.ZERO;
                        final var length = offset - currentOffset;

                        final var stringWithInformationOfNonZeroArea = "Offset: " + currentOffset + " length: " + length + System.lineSeparator();
                        final var arrayOfBytes = stringWithInformationOfNonZeroArea.getBytes();

                        outputStream.write(arrayOfBytes);
                    }
                    if (byteAsInt == -1) {
                        break outer;
                    }
                    break;

                case ZERO:
                    if (byteAsInt == -1) {
                        break outer;
                    }

                    if (readByte != 0) {
                        state = State.NONZERO;
                        currentOffset = offset;
                    }
                    break;
            }
            offset++;
        }


    }
}


enum State {
    NONZERO,
    ZERO,
}