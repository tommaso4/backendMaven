package org.week2.day4_Streem2File;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    List<Product> products;
    Costumer costumer;

    public Order(Long id, String status, List<Product> products, Costumer costumer, LocalDate date) {
        this.id = id;
        this.status = status;
        this.orderDate = date;
        this.deliveryDate = orderDate.plusDays(3);
        this.products = products;
        this.costumer = costumer;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void addProducr(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", costumer=" + costumer +
                '}';
    }


}
