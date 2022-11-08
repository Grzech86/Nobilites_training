package Nobilities.ex40;
/*
Zadanie 40
Command pattern
Implement calculator using command design pattern:
add operation
mul operation
clear operation
replay operation
*/

public interface Calculator {
    void add(CalculatorOperation operation);
    int result();
}
