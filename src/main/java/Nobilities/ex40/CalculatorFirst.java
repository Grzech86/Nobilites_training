package Nobilities.ex40;

import java.util.LinkedList;
import java.util.List;

public class CalculatorFirst implements Calculator {
    private final List<CalculatorOperation> history = new LinkedList<>();
    
    @Override
    public void add(CalculatorOperation operation) {
        if (operation instanceof CalculatorOperation.Replay replay) {
            rewrite(replay);
        } else {
            history.add(operation);
        }
    }


    @Override
    public int result() {
        int result = 0;

        for(final var operation : history) {
            if (operation instanceof CalculatorOperation.Add actualAdd) {
                result += actualAdd.a();
            } else if (operation instanceof CalculatorOperation.Mul actualMul) {
                result *= actualMul.a();
            } else if (operation instanceof CalculatorOperation.Clear) {
                result = 0;
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }

    private void rewrite(CalculatorOperation.Replay replay) {
        final var newHistory = history.subList(0, replay.n());
        history.clear();
        history.addAll(newHistory);
    }
}
