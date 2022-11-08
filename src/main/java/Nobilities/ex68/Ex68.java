package Nobilities.ex68;

//## Inheritance
//        Having classes:
//        ```java
//public abstract class Person {
//}
//
//public class Employee {
//
//}
//
//public class Address {
//}
//```
//        write JUnit tests checking
//        - Person
//        - Person class should be abstract
//  - Person class should not have methods
//  - Person class should have address field.
//        - Employee
//        - Employee class should be a subclass of Person class
//  - Employee class should have address
//  - Employee class should have salary
//  - Employee class should have method promote which increase salary by given number
//- Address
//        - Should have private fields: address, postCode, city
public class Ex68 {

    public abstract class Person {

       private Address address;
    }

    public class Employee extends Person {

        }


    public class Address {
        public Address address;
//        public PostCode postCode;
//        public City city;
    }
}
