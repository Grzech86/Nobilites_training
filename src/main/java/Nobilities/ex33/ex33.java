package Nobilities.ex33;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Join orders and customers
public class ex33 {

    class Data {
        public static String[] orders2 = {
                //order id, customer id, date
                "10308,2,1996-09-18",
                "10309,37,1996-09-19",
                "10310,77,1996-09-20"
        };

        public static String[] customers2 = {
                // customer id, name, country
                "1,Alfreds Futterkiste,Germany",
                "2,Ana Trujillo,Mexico",
                "3,Antonio Moreno,Mexico"
        };

        public static List<Orders> decode(String[] orders2) {
            final var orders = new ArrayList<Orders>();

            for (final var line : orders2) {
                orders.add(Orders.decode(line));
            }
            return orders;
        }

        public static List<Customers> decode2(String[] customers2) {
            final var customers = new ArrayList<Customers>();

            for (final var line : customers2) {
                customers.add(Customers.decode2(line));
            }
            return customers;
        }

        public static void main(String[] args) {
            final var orders = decode(orders2);


            Map<Integer, List<Orders>> orderMap = new HashMap<>();
            for (final var order : orders) {
                final var orderID = order.orderID;

                if (!orderMap.containsKey(orderID)) {
                    orderMap.put(orderID, new ArrayList<>());
                }

                final var list = orderMap.get(orderID);
                list.add(order);
            }
            final var customers = decode2(customers2);

            Map<String, List<Customers>> customerMap = new HashMap<>();
            for (final var customer : customers) {
                final var name = customer.name;

                if (!customerMap.containsKey(name)) {
                    customerMap.put(name, new ArrayList<>());
                }

                final var list = customerMap.get(name);
                list.add(customer);

            }
        }
    }
}

class Orders {
    final int orderID;
    final int customerID;
    final String date;

    public Orders(int orderID, int customerID, String date) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
    }

    static Orders decode(String line) {
        String[] elements = line.split(",");

        return new Orders(
                Integer.valueOf(elements[0]),
                Integer.valueOf(elements[1]),
                elements[2]
        );


    }
}

class Customers {

    final int customrtID;
    final String name;
    final String country;

    public Customers(int customrtID, String name, String country) {
        this.customrtID = customrtID;
        this.name = name;
        this.country = country;
    }

    static Customers decode2(String line) {
        String[] elements = line.split(",");

        return new Customers(
                Integer.valueOf(elements[0]),
                elements[1],
                elements[2]

        );


    }

}