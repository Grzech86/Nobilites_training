package Nobilities.ex67;

public class CalculatorMain {

    //
//##Calculator
//        Having a create function for "impossible" calculator
//        java
//public class CalculatorMain {
//    public Object create() {
//        return new Object() {
//            // todo implement me
//        };
//    }
//}
//    Define a set of tests checking if a returned object:
//        - implements method "add" taking two arguments of type int and returning type int
//        - correctly adds two integer numbers

    public static Object create() {
        return new Object() {
            public int add(int a, int b) {
                return a + b;
            }
            // todo implement me
        };
    }
}