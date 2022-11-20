package Nobilites.ex36ToDo;

import Nobilities.ex36ToDo.ID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDTest {

    @Test
    void testValid() {
        final var id0 = ID.create("ala");
        Assertions.assertInstanceOf(ID.Valid.class, id0);
    }

    @Test
    void testInvalid() {
        final var id0 = ID.create("ala ma kota");
        Assertions.assertInstanceOf(ID.Invalid.class, id0);
    }

    @Test
    void testInvalidBecauseStartingWithDigit() {
        final var id0 = ID.create("0ala");
        Assertions.assertInstanceOf(ID.Invalid.class, id0);
    }

    @Test
    void testValidAdvanced() {
        final var id0 = ID.create("aL12ab");
        Assertions.assertInstanceOf(ID.Valid.class, id0);
    }
}