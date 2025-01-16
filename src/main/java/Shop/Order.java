package Shop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Order extends HashMap<Product, Integer> {
    private final Long id = new Random().nextLong();
    private final Customer customer;
    private LocalDate orderDate, deliveryDate;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order(Customer customer, LocalDate orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public Order(Customer customer, LocalDate orderDate, LocalDate deliveryDate) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void order() {
        this.orderDate = LocalDate.now();
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String getStatus() {
        if (this.getDeliveryDate() != null) return "Delivered";
        if (this.getOrderDate() != null) return "Pending";
        return "Waiting to be ordered";
    }

    public void addProduct(Product product) {
        super.put(product, super.getOrDefault(product, 0) + 1);
    }

    public void addProduct(Product product, int quantity) {
        if (quantity < 1)
            throw new IllegalArgumentException("Quantity must be greater or equal 1");
        super.put(product, super.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        Integer n = super.get(product);
        if (n == null) return;
        if (n == 1) super.remove(product);
        super.replace(product, n - 1);
    }

    public double getTotal() {
        return super.keySet().stream()
                .mapToDouble(product -> product.getPrice() * super.get(product))
                .sum();
    }

    public Long getNumberOfProducts() {
        return this.keySet().stream()
                .mapToLong(this::get)
                .count();
    }

    @Override
    public String toString() {
        StringBuilder orders = new StringBuilder();
        for (Product product : this.keySet())
            orders
                    .append(this.get(product))
                    .append("x ")
                    .append(product)
                    .append("\n");

        return """
                Order{%s
                %s}""".formatted(this.getCustomer(), orders);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customer, order.customer) && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, customer, orderDate, deliveryDate);
    }
}