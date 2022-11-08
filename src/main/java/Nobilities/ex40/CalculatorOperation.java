package Nobilities.ex40;

public sealed interface CalculatorOperation {
    record Add(int a) implements CalculatorOperation{}
    record Mul(int a) implements CalculatorOperation{}
    record Clear() implements CalculatorOperation{}
    record Replay(int n) implements CalculatorOperation{}
}
