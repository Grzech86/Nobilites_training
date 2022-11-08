package Nobilities.ex67;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Ex67 {

    public static void inspect (Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        final var claz = object.getClass();
        System.out.println(claz);
        System.out.println(Arrays.asList(claz.getConstructors()));
        System.out.println(claz.getName());
        System.out.println(Arrays.asList(claz.getDeclaredFields()));
        System.out.println(Arrays.asList(claz.getInterfaces()));
        System.out.println(Arrays.asList(claz.getMethods()));

        Field name = claz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(object, "Stefanek");

        Method method = claz.getDeclaredMethod("meow");
        System.out.println(method);
        method.invoke(object);
    }
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Cat cat = new Cat("Luna");
        inspect(cat);
    }
}

class Cat implements Cloneable {
    private final String name;
    public Cat(String name) {
        this.name = name;
    }
    public void meow() {
        System.out.println(name + " say \"meow\"");
    }
    public Cat create(String name) {
        return new Cat("name: " + name);
    }
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
