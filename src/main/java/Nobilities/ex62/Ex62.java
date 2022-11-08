package Nobilities.ex62;
//# Non-zero Analysis
//Create an application that analyzes bigfile.dat (uncompress bigfile.dat.bz2)
// and lists all non-zeros parts of the file in the form: offset, length.
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ex62 {

    static final int NONZERO = 10;
    static final int ZERO = 1000;
    public static void main(String[] args) {
        try (final var is = new BufferedInputStream(Files.newInputStream(Path.of("bigfile.dat")))) {
            int state = NONZERO;

            long currentOffset = 0;
            long offset = 0;
            for (; ; ) {
                final var r = is.read();
                if (r == -1) {
                    // do something on exit
                    break;
                }
                final var b = (byte) r;

                if (state == NONZERO) {
                    if (b == 0) {
                        state = ZERO;
                        final var length = offset - currentOffset;
                        System.out.println("Nonzero area at offset " + currentOffset + " with " + length + " bytes");
                    }
                } else if (state == ZERO) {
                    if (b != 0) {
                        state = NONZERO;
                        currentOffset = offset;
                    }
                } else {
                    throw new RuntimeException("unknown state of value " + state);
                }
                offset++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//enum State {
//    NONZERO,
//    ZERO
//}
class Analysis {

    public static void main(String[] args) {
        try (final var is = new BufferedInputStream(Files.newInputStream(Path.of("bigfile.dat")))) {
            State state = State.NONZERO;
            long currentOffset = 0;
            long offset = 0;
            outer: for (; ; ) {
                final var r = is.read();
                final var b = (byte) r;
                switch (state) {
                    case NONZERO:
                        if (r == -1 || b == 0) {
                            state = State.ZERO;
                            final var length = offset - currentOffset;
                            System.out.println("Nonzero area at offset " + currentOffset + " with " + length + " bytes");
                        }
                        if (r == -1) {
                            // do something on exit
                            break outer;
                        }
                        break;
                    case ZERO:
                        if (r == -1) {
                            // do something on exit
                            break outer;
                        }

                        if (b != 0) {
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

 enum State {
    NONZERO,
    ZERO,
}