package Nobilites.example;

import Nobilities.example.Customer;
import Nobilities.example.CustomizedOrder;
import Nobilities.example.Joiner;
import Nobilities.example.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JoinTest {
    @Test
    public void testSingleRow() {
        final var customers = List.of(new Customer("clt1", 1));
        final var orders = List.of(new Order(17, 1, "prd1"));

        final var join = new Joiner();

        final var expected =
                List.of(new CustomizedOrder(17, "clt1", "prd1"));

        final var result = join.inner(customers, orders);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testTwoOrderOneCustomer() {
        final var customers = List.of(new Customer("clt1", 1));
        final var orders = List.of(
                new Order(63, 2, "prd2"),
                new Order(17, 1, "prd1")
        );

        final var join = new Joiner();

        final var expected =
                List.of(new CustomizedOrder(17, "clt1", "prd1"));

        final var result = join.inner(customers, orders);

        Assertions.assertEquals(expected, result);

    }
}
