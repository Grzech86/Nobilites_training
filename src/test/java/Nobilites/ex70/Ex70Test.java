package Nobilites.ex70;

import Nobilities.ex70.CalculatorMockito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Answer1;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//## Mock Calculator
//        Implement calculator using mockito object:
//        - add operation
//        - mul operation
//        - clear operation
//        - get last value
public class Ex70Test {

    CalculatorMockito mock = mock(CalculatorMockito.class);

    @Test
    public void testCalculatorAdd() {
        when(mock.add(anyInt(), anyInt())).thenAnswer(
                (Answer) invocation -> {
                    Object[] args = invocation.getArguments();
                    Object mock = invocation.getMock();
                    if (args[0] instanceof Integer a && args[1] instanceof Integer b) {
                        return a + b;
                    }
                    throw new UnsupportedOperationException();
                });

        Assertions.assertEquals(3, mock.add(1, 2));
    }
    @Test
    public void testCalculatorMul() {
        when(mock.mul(anyInt(), anyInt())).thenAnswer(
                (Answer) invocation -> {
                    Object[] args = invocation.getArguments();
                    Object mock = invocation.getMock();
                    if (args[0] instanceof Integer a && args[1] instanceof Integer b) {
                        return a * b;
                    }
                    throw new UnsupportedOperationException();
                });

        Assertions.assertEquals(9, mock.mul(3, 3));
    }
    @Test
    public void testCalculatorClear() {


        when(mock.clear(anyCollection().size())).thenAnswer((Answer) invocation -> {
            Object[] args = invocation.getArguments();
            Object mock = invocation.getMock();
            if (args[0] instanceof Integer a && args[1] instanceof Integer b) {
                return a * b;
            }
            throw new UnsupportedOperationException();
        });
        Assertions.assertEquals(0, mock.mul(3,3));


//        when(mock.clear(anyInt())).thenAnswer(
//                (Answer) invocation -> {
//                    Object[] args = invocation.getArguments();
//                    Object mock = invocation.getMock();
//                    if (args[0] instanceof Integer c) {
//                        return c  ;
//                    }
//                    throw new UnsupportedOperationException();
//                });
//
//
//        Assertions.assertEquals(0, mock.clear(0));



    }

}