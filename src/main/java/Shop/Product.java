package Shop;

import java.util.*;

public class Product {
    private final Long id;
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.id = new Random().nextLong();
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static Product es3_(Product p1, Product p2) {
        if (p1 == null && p2 == null) return null;
        if (p1 == null) return p2;
        if (p2 == null) return p1;
        return p1.getPrice() < p2.getPrice() ? p2 : p1;
    }

    public static Product es3(List<Product> lista) {
        return lista.stream().reduce(
                null,
                Product::es3_,
                Product::es3_
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategory(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{%s: %.2f€}".formatted(this.getName(), this.getPrice());
    }
}