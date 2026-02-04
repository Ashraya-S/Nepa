package com.example.nepa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private Date date;
    private double total;
    private List<CartItem> items;
    private String paymentMethod;

    public Order(String id, Date date, double total, List<CartItem> cartItems, String paymentMethod) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.items = new ArrayList<>(cartItems);
        this.paymentMethod = paymentMethod;
    }

    public String getId() { return id; }
    public Date getDate() { return date; }
    public double getTotal() { return total; }
    public List<CartItem> getItems() { return items; }
    public String getPaymentMethod() { return paymentMethod; }
}
