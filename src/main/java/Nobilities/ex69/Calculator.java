package Nobilities.ex69;

import Nobilities.Account.Main;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Proxy;

//## Proxy Calculator
//        Implement calculator using proxy object:
//        - add operation
//        - mul operation
//        - clear operation
public class Calculator {
    public static void main(String[] args) {
        final var p = Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Calc.class},
                (proxy, method, params) -> {
                    if (method.getName() == "add") {
                        return (Integer) params[0] + (Integer) params[1];


                    } else if (method.getName() == "mul") {
                        return (Integer) params[0] * (Integer) params[1];

                    } else if (method.getName() == "clear") {
                        System.out.println("Calc is clear");
                        return null;
                    }

                    throw new OperationNotSupportedException();
                });



        Calc calc = (Calc) p;
        System.out.println(calc.add(1,2));
        System.out.println(calc.mul(3,3));
        calc.clear();
    }

}
interface Calc{
    int add(int a, int b);
    int mul(int a, int b);
    String clear();

}