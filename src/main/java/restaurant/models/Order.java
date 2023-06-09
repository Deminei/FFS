package restaurant.models;

import restaurant.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<MenuItem> items;
    private double totalPrice;
    private String status;
    private int tableID;

    public Order(int orderId, List<MenuItem> items, double totalPrice, String status, int tableID) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.tableID = tableID;
    }

    public Order() {
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTable() {
        return tableID;
    }

    public void setTable(Table table) {
        this.tableID = tableID;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
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

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }
}

