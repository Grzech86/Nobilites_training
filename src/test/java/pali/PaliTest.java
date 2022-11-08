package pali;

import Nobilities.pali.Palin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PaliTest {

    @Test
    public void FirstTestPali() {
            final var palindrom0 = List.of('(', '(', ')', ')');
            final var pali = new Palin();
            final var expected = true;

            final var result = pali.stringToCheck(palindrom0);

            Assertions.assertTrue(result);
        }


}


