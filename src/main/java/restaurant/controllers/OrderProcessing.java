package restaurant.controllers;
import restaurant.models.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessing {
    private static Table table;
    private final List<Order> activeOrders;
    private Map<Integer, String> orderStatus;
    private ExecutorService executorService;
    private InventoryManagement inventoryManagement;
//    private SalesReport salesReport;

    public OrderProcessing() {
        activeOrders = new ArrayList<>();
        orderStatus = new HashMap<>();
        executorService = Executors.newFixedThreadPool(5); // Set the number of threads as desired
        inventoryManagement = new InventoryManagement();
        Table TableManagement = table;
//        salesReport = new SalesReport();
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
//        notifyStaff(order);
    }
    public void updateOrderStatus(int orderId, String newStatus) {
        String put = orderStatus.put(orderId, newStatus);
    }
    public double calculateTotalPrice(int orderId) {
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                double totalPrice = 0.0;
                for (OrderItem item : order.getItems()) {
                    totalPrice += item.getPrice();
                }
                return totalPrice;
            }
        }
        return 0.0; // Order not found
    }
    private void generateSalesReport() {
        List<Order> completedOrders = getCompletedOrders();
        SalesReport.generateDailySalesReport(completedOrders);
    }
    private void updateInventory(Order order) {
        // Implement the logic to update the inventory based on the items in the order
        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            inventoryManagement.useIngredient(item.getMenuItem().getName());
        }
    }
    private List<Table> getTables() {
        // Implement this method to retrieve the list of tables from TableManagement
        TableManagement tableManagement = new TableManagement();
        return tableManagement.getTables();
    }
    public static void placeGuestOrder(OrderProcessing orderProcessing) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display the menu
            System.out.println("Menu:");
            List<MenuItem> menuItems = MenuManagement.getMenuItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItemData = menuItems.get(i);
                String itemName = menuItemData.getName();
                String description = menuItemData.getDescription();
                double price = Double.parseDouble(String.valueOf(menuItemData.getPrice()));
                System.out.println(i + 1 + ". " + itemName + " - " + description + " - $" + price);
            }

            // Prompt the guest to select an item
            System.out.println("Enter the number of the item you want to order (or 0 to finish ordering):");
            int itemNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the new line character

            if (itemNumber == 0) {
                running = false;
            } else if (itemNumber < 1 || itemNumber > menuItems.size()) {
                System.out.println("Invalid item number. Please try again.");
            } else {
                MenuItem menuItemData = menuItems.get(itemNumber);
                String itemName = menuItemData.getName();
                String description = menuItemData.getDescription();
                double price = Double.parseDouble(String.valueOf(menuItemData.getPrice()));

                // Prompt the guest to enter the quantity
                System.out.println("Enter the quantity:");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the new line character

                // Create the order item
                MenuItem menuItem = new MenuItem(itemName, description, price);
                OrderItem orderItem = new OrderItem(menuItem, quantity);

                // Create the order
                Order order = new Order();
                order.setItems((List<OrderItem>) orderItem);

                // Process the order
                orderProcessing.createOrder(order);

                System.out.println("Order placed successfully.");
            }
        }
    }



        private void notifyStaff(Order order) {
        List<MenuItem> items = order.getItems();
        for (MenuItem item : items) {
            List<String> ingredients = item.getIngredients();
            for (String ingredient : ingredients) {
                InventoryItem inventoryItem = InventoryManagement.getIngredients(ingredient);
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
        return Collections.unmodifiableList(activeOrders);
    }
}
// going to bed now ᇂ_ᇂ