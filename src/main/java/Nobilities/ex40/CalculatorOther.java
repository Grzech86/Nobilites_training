package Nobilities.ex40;

import java.util.LinkedList;
import java.util.List;

public class CalculatorOther implements Calculator {
    private final List<Integer> history = new LinkedList<>();

    int result = 0;

    public void add(CalculatorOperation operation) {
        if (operation instanceof CalculatorOperation.Add actualAdd) {
            result += actualAdd.a();
            history.add(result);
        } else if (operation instanceof CalculatorOperation.Mul actualMul) {
            result *= actualMul.a();
            history.add(result);
        } else if (operation instanceof CalculatorOperation.Clear) {
            history.clear();
            result = 0;
        } else if (operation instanceof CalculatorOperation.Replay replay) {
            result = history.get(replay.n() - 1);
            final var newHistory = List.copyOf(history.subList(0, replay.n()));
            history.clear();
            history.addAll(newHistory);
        }
    }


    public int result() {
        return result;
    }
}
