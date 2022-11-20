package Nobilities.ex69;


import Nobilities.Account.Main;

import java.lang.reflect.Proxy;

public class Ex69 {
    public static void main(String[] args) {

        final var p = Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Cat.class},
                (proxy, method, params) -> {
                    if (method.getName() == "meow") {
                        System.out.println("Cat says \"meow\"");
                    } else if (method.getName() == "drop") {
                        final var count = (Integer) params[0];
                        System.out.println("Cat dropped " + count + " objects");
                    } else if (method.getName() == "isHungry") {
                        return true;
                    }

                    return null;
                });

        Cat cat = (Cat) p;
        cat.meow();
        cat.drop(2);
        System.out.println(cat.isHungry());
    }
}

interface Cat {
    void meow();
    void drop(int k);

    boolean isHungry();
}
