package ex40;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAddOperations() {
        CalculatorFirst calculator = new CalculatorFirst();

        calculator.add(new CalculatorOperation.Add(2));
        calculator.add(new CalculatorOperation.Add(3));

        int actual = calculator.result();

        Assertions.assertEquals(5, actual);
    }

    @Test
    public void testMulOperations() {
        CalculatorFirst calculator = new CalculatorFirst();

        calculator.add(new CalculatorOperation.Add(1));
        calculator.add(new CalculatorOperation.Mul(7));

        int actual = calculator.result();

        Assertions.assertEquals(7, actual);
    }

    @Test
    public void testClearOperations() {
        CalculatorFirst calculator = new CalculatorFirst();

        calculator.add(new CalculatorOperation.Add(1));
        calculator.add(new CalculatorOperation.Mul(7));
        Assertions.assertEquals(7, calculator.result());

        calculator.add(new CalculatorOperation.Clear());

        Assertions.assertEquals(0, calculator.result());
    }

    @Test
    public void testReplyOperations() {
        Calculator calculator = new CalculatorOther();

        calculator.add(new CalculatorOperation.Add(1));
        calculator.add(new CalculatorOperation.Mul(7));
        calculator.add(new CalculatorOperation.Add(2));
        Assertions.assertEquals(9, calculator.result());

        calculator.add(new CalculatorOperation.Replay(2));

        Assertions.assertEquals(7, calculator.result());
    }

}
