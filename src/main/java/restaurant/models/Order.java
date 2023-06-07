package restaurant.models;

import restaurant.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> items;
    private double totalPrice;
    private String status;
    private Table table;

    public Order(int orderId, List<OrderItem> items, double totalPrice, String status, Table table) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.table = table;
    }

    public Order() {
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public OrderItem getItem(int index) {
        return items.get(index);
    }
}

