package Nobilities.example;

import java.util.List;

public class Joiner {

    public List<CustomizedOrder> inner(List<Customer> customers, List<Order> orders) {

        for (int i = 0; i < orders.size(); i++ ) {

            final var order = orders.get(i);

            final var customer = customers.get(0);



            return List.of(new CustomizedOrder(order.id(), customer.name(), order.product()));
        }
        return null;
    }
}