package org.example;

import Shop.*;

import java.util.*;
import java.util.stream.Collectors;

public class Shop extends HashSet<Order> {

    public Shop() {
        super();
    }

    public Shop(Order... orders) {
        super(List.of(orders));
    }

    public Shop(Collection<? extends Order> c) {
        super(c);
    }

    public List<Order> getAllOrderOf(Customer customer) {
        return this.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList();
    }

    public Map<Customer, List<Order>> es1() {
        return this.stream()
                .collect(Collectors.toMap(
                        order -> order.getCustomer(),
                        order -> Arrays.stream(new Order[]{order}).toList(),
                        (order1, order2) -> {
                            List<Order> orders = new ArrayList<>(order1);
                            orders.addAll(order2);
                            return orders;
                        }
                ));
    }

    public Map<Customer, Double> es2() {
        return this.stream()
                .collect(Collectors.toMap(
                        order -> order.getCustomer(),
                        order -> order.getTotal(),
                        Double::sum
                ));
    }

    public Double es4() {
        return this.stream().collect(Collectors.averagingDouble(order -> order.getTotal()));
    }
}