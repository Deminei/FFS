package restaurant.controllers;
import restaurant.models.MenuItem;
import restaurant.models.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessing {
    private final List<Order> activeOrders;
    private Map<Integer, String> orderStatus;
    private ExecutorService executorService;
    private InventoryManagement inventoryManagement;
    private SalesReport salesReport;

    public OrderProcessing() {
        activeOrders = new ArrayList<>();
        orderStatus = new HashMap<>();
        executorService = Executors.newFixedThreadPool(5); // Set the number of threads as desired
        inventoryManagement = new InventoryManagement();
        salesReport = new SalesReport();
    }
    public void createOrder(Order order) {
        activeOrders.add(order);
        orderStatus.put(order.getOrderId(), "Waiting");

        // Process the order asynchronously
        executorService.submit(() -> processOrder(order));
    }
    private void processOrder(Order order) {
        // Simulate order processing time
        try {
            Thread.sleep(2000); // Sleep for 2 seconds to simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order processing interrupted.");
            return;
        }
        // Update order status
        updateOrderStatus(order.getOrderId(), "Preparing order.");

        // Calculate total price
        double totalPrice = calculateTotalPrice(order.getOrderId());

        // Perform other order processing tasks
        // such as updating inventory, notifying staff, generating reports
        try {
            Thread.sleep(5000);// Sleep for 5 seconds to simulate processing time.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Kitchen is having a strike!");
            return;
        }
        // Update order status to "Ready" when processing is complete
        updateOrderStatus(order.getOrderId(), "Order is ready!");

        // Additional tasks after order processing is complete
        generateSalesReport();
        updateInventory(order);
        notifyStaff(order);
    }
    public void updateOrderStatus(int orderId, String newStatus) {
        orderStatus.put(orderId, newStatus);
    }
    public double calculateTotalPrice(int orderId) {
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                double totalPrice = 0.0;
                for (MenuItem item : order.getItems()) {
                    totalPrice += item.getPrice();
                }
                return totalPrice;
            }
        }
        return 0.0; // Order not found
    }
    private void generateSalesReport() {
        List<Order> completedOrders = getCompletedOrders();
        salesReport.generateDailySalesReport(completedOrders);
    }
    private void updateInventory(Order order) {
        // Implement the logic to update the inventory based on the items in the order
        List<MenuItem> items = order.getItems();
        for (MenuItem item : items) {
            inventoryManagement.useIngredient(item.getName());
        }
    }
    private List<Table> getTables() {
        // Implement this method to retrieve the list of tables from TableManagement
        TableManagement tableManagement = new TableManagement();
        return tableManagement.getTables();
    }

    private void notifyStaff(Order order) {
        List<MenuItem> items = order.getItems();
        for (MenuItem item : items) {
            List<String> ingredients = item.getIngredients();
            for (String ingredient : ingredients) {
                InventoryItem inventoryItem = inventoryManagement.getIngredient(ingredient);
                if (inventoryItem.getQuantity() <= inventoryItem.getThreshold()) {
                    System.out.println("Alert: Low quantity for ingredient " + ingredient);
                }
            }
        }
    }
    private List<Order> getCompletedOrders() {
        List<Order> completedOrders = new ArrayList<>();
        for (Order order : activeOrders) {
            if (orderStatus.getOrDefault(order.getOrderId(), "").equals("Order is ready!")) {
                completedOrders.add(order);
            }
        }
        return completedOrders;
    }
    public void clearAllOrders() {
        activeOrders.clear();
    }
    public int getActiveOrderCount() {
        return activeOrders.size();
    }
    //??Additional methods?

    // Getter for activeOrders
    public List<Order> getActiveOrders() {
        return activeOrders;
    }
}
// going to bed now ᇂ_ᇂ