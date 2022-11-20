package Nobilites.ex67;

import Nobilities.ex67.CalculatorMain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class CalculatorMainTest {
    @Test
    public void testMethodAddExists() {
        final var calculator = CalculatorMain.create();
        final var clazz = calculator.getClass();

        final var method = Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("add", int.class, int.class), "method add(int, int) does not exist");

        final var returnedType = method.getReturnType();
        Assertions.assertEquals(int.class, returnedType, "return type is not int");

    }
    @Test
    public void testCorrectResoult() throws InvocationTargetException, IllegalAccessException {
        final var calculator = CalculatorMain.create();
        final var clazz = calculator.getClass();

        final var method = Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("add", int.class, int.class), "method add(int, int) does not exist");
        Assertions.assertEquals(4,method.invoke(calculator,1, 3));

    }
}