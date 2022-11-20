package Nobilites.ex68;

import Nobilities.ex68.Ex68;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Ex68Test {
    @Test
    public void testCheckingPerson(){
        final var clazz = Ex68.Person.class;
        final var modifiers = clazz.getModifiers();
        final var result = Modifier.isAbstract(modifiers);
        Assertions.assertTrue(result);
    }
    @Test
    public void testNotHaveMethods(){
        final var clazz = Ex68.Person.class;
        final var result = clazz.getDeclaredMethods().length == 0;
        Assertions.assertTrue(result);
    }
    @Test
    public void testPersonHaveAdres() throws  NoSuchMethodException {
        final var clazz = Ex68.Person.class;
        final var fields = clazz.getFields();
        Assertions.assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("address")));
//        Assertions.assertTrue(Arrays.stream(fields).map(Field::getName).anyMatch("address"::equals));
    }

    @Test
    public void testSubclassOfPerson(){
        final var clazz = Ex68.Employee.class;
        final var modifiers = clazz.getModifiers();
        final var result = Modifier.isInterface(modifiers);
        Assertions.assertTrue(result);


    }
//    @Test
//    public void testAddressHaveAddress() throws  NoSuchMethodException {
//        final var clazz = Ex68.Address.class;
//        final var method = clazz.getDeclaredMethod("address");
//        Assertions.assertTrue(method);
//}
}