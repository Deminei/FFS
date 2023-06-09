package restaurant.controllers;
import restaurant.models.*;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class OrderProcessing{
    private List<Order> totalOrders;
    private List<Order> ordersInWaiting;
    private List<Order> ordersInPrep;
    private List<Order> ordersComplete;
    private Map<Integer, String> orderStatus;
    private ExecutorService executorService;

   public OrderProcessing(){
       totalOrders = new ArrayList<>();
       ordersInWaiting = new ArrayList<>();
       ordersInPrep = new ArrayList<>();
       ordersComplete = new ArrayList<>();
       orderStatus = new HashMap<>();
       executorService = Executors.newFixedThreadPool(5);
   }

   public void placingOrder(TableManagement tableManagement, MenuManagement menu, InventoryManagement inventory){

       Scanner scanner = new Scanner(System.in);
       int orderID = totalOrders.size();

       boolean running = true;
        Order newOrder = new Order();

        // Select an occupied table
       tableManagement.checkTables();
       System.out.println("Enter the ID of the table where this order will be going: ");
       int possibleTableID = Integer.parseInt(scanner.nextLine());

       for (Table table : tableManagement.tables){
           if(table.getTableId() == possibleTableID && Table.Status.valueOf(table.getStatus().toString()).equals("OCCUPIED")){
               int tableID = possibleTableID;
           } else{
               System.out.println("Check inputs. Table may not exist or is currently unoccupied.");
           }
       }


       while (running) {
           // Display the menu
           System.out.println("Menu:");
           menu.displayMenuItems();

//           Prompt the guest to select an item
           System.out.println("Enter the number corresponding to the item you want to order (or 0 to finish ordering):");
           int itemNumber = Integer.parseInt(scanner.nextLine());

           if (itemNumber == 0) {
               running = false;
           } else if (itemNumber < 0 || itemNumber > menu.getMenuItems().size()) {
               System.out.println("Invalid item number. Please try again.");
           } else {
               MenuItem menuItemData = menu.getMenuItems().get(itemNumber - 1);
               String itemName = menuItemData.getName();
               String description = menuItemData.getDescription();
               double price = menuItemData.getPrice();

               // Prompt the guest to enter the quantity
               System.out.println("Enter the quantity:");
               int quantity = Integer.parseInt(scanner.nextLine());

                ArrayList<MenuItem> itemsToAdd = new ArrayList<>();
//              Create the list of items to order
                for(MenuItem item : menu.getMenuItems()){
                    if (item.getName().equals(itemName)){
                        itemsToAdd.add(item);
                    }
                }
               System.out.println("Item added to the order.");

//                OrderItem newOrderItem = new OrderItem(itemToOrder.)
//                Order newOrder = new Order(orderID, itemToOrder,)


            }
        }

   //executorService.submit(() -> processOrder(order));
   }
}


//public class OrderProcessing {
//    private static Table table;
//    private static Scanner scanner;
//    private static OrderProcessing orderProcessing;
//    private final List<Order> activeOrders;
//    private Map<Integer, String> orderStatus;
//    private ExecutorService executorService;
//    private InventoryManagement inventoryManagement;
//    public static TableManagement tableManagement;
////    private SalesReport salesReport;
//
//    public OrderProcessing() {
//        activeOrders = new ArrayList<>();
//        orderStatus = new HashMap<>();
//        executorService = Executors.newFixedThreadPool(5); // Set the number of threads as desired
//        inventoryManagement = new InventoryManagement();
//        tableManagement = new TableManagement();
////        salesReport = new SalesReport();
//    }
//
//    public void createOrder(Order order) {
//        activeOrders.add(order);
//        orderStatus.put(order.getOrderId(), "Waiting");
//
//        // Process the order asynchronously
//        executorService.submit(() -> processOrder(order));
//    }
//
//    private void processOrder(Order order) {
//        // Simulate order processing time
//        try {
//            Thread.sleep(2000); // Sleep for 2 seconds to simulate processing time
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.out.println("Order processing interrupted.");
//            return;
//        }
//        // Update order status
//        updateOrderStatus(order.getOrderId(), "Preparing order.");
//
//        // Calculate total price
//        double totalPrice = calculateTotalPrice(order.getOrderId());
//
//        // Perform other order processing tasks
//        // such as updating inventory, notifying staff, generating reports
//        try {
//            Thread.sleep(5000);// Sleep for 5 seconds to simulate processing time.
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.out.println("Kitchen is having a strike!");
//            return;
//        }
//        // Update order status to "Ready" when processing is complete
//        updateOrderStatus(order.getOrderId(), "Order is ready!");
//
//        // Additional tasks after order processing is complete
////        generateSalesReport();
//        updateInventory(order);
////        notifyStaff(order);
//    }
//
//    public void updateOrderStatus(int orderId, String newStatus) {
//        String put = orderStatus.put(orderId, newStatus);
//    }
//
//    public double calculateTotalPrice(int orderId) {
//        for (Order order : activeOrders) {
//            if (order.getOrderId() == orderId) {
//                double totalPrice = 0.0;
//                for (OrderItem item : order.getItems()) {
//                    totalPrice += item.getTotalPrice();
//                }
//                return totalPrice;
//            }
//        }
//        return 0.0; // Order not found
//    }
//
//    private void generateSalesReport(SalesReport report) {
//        List<Order> completedOrders = getCompletedOrders();
//        report.generateDailySalesReport(completedOrders);
//    }
//
//    private void updateInventory(Order order) {
//        // Implement the logic to update the inventory based on the items in the order
//        List<OrderItem> items = order.getItems();
//        for (OrderItem item : items) {
//            inventoryManagement.useIngredient(item.getMenuItem().getName());
//        }
//    }
//
////    private List<Table> getTables() {
////        // Implement this method to retrieve the list of tables from TableManagement
////        TableManagement tableManagement = new TableManagement();
////        return tableManagement.getTables();
////    }
//
//    public static void placeGuestOrder(MenuManagement menu, Table table, InventoryManagement inventory) {
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//        Order order = new Order();
//
//        // Select an occupied table
////        Table assignedTable = selectOccupiedTable();
////        if (assignedTable == Table.Status.AVAILABLE) {
////            System.out.println("No occupied tables available. Please try again later.");
////            return; // Exit the method if no occupied tables are available
////        }
////
////        // Assign the table to the order
////        order.setTable(assignedTable);
//
//        while (running) {
//            // Display the menu
//            System.out.println("Menu:");
//            List<MenuItem> menuItems = menu.getMenuItems();
//            for (int i = 0; i < menuItems.size(); i++) {
//                MenuItem menuItemData = menuItems.get(i);
//                String itemName = menuItemData.getName();
//                String description = menuItemData.getDescription();
//                double price = menuItemData.getPrice();
//                System.out.println(i + 1 + ". " + itemName + " - " + description + " - $" + price);
//            }
//
//            // Prompt the guest to select an item
//            System.out.println("Enter the number of the item you want to order (or 9 to finish ordering):");
//            int itemNumber = scanner.nextInt();
//            scanner.nextLine(); // Consume the new line character
//
//            if (itemNumber == 9) {
//                running = false;
//            } else if (itemNumber < 0 || itemNumber > menuItems.size()) {
//                System.out.println("Invalid item number. Please try again.");
//            } else {
//                MenuItem menuItemData = menuItems.get(itemNumber - 1);
//                String itemName = menuItemData.getName();
//                String description = menuItemData.getDescription();
//                double price = menuItemData.getPrice();
//
//                // Prompt the guest to enter the quantity
//                System.out.println("Enter the quantity:");
//                int quantity = scanner.nextInt();
//                scanner.nextLine(); // Consume the new line character
//
//                // Create the order item
//                MenuItem menuItem = new MenuItem(itemName, description, price);
//                OrderItem orderItem = new OrderItem(menuItem, quantity);
//
//                // Add the order item to the order
//                order.addItem(orderItem);
//
//                System.out.println("Item added to the order.");
//            }
//        }
//
//        // Process the order
// //       orderProcessing.createOrder(order);
//        System.out.println("Order placed successfully.");
//
//        // Display the order contents
//        System.out.println("Order details:");
//        List<OrderItem> orderItems = order.getItems();
//        for (int i = 0; i < orderItems.size(); i++) {
//            OrderItem orderItem = orderItems.get(i);
//            MenuItem menuItem = orderItem.getMenuItem();
//            int quantity = orderItem.getQuantity();
//            double totalPrice = orderItem.getTotalPrice();
//
//            System.out.println(i + 1 + ". " + menuItem.getName() + " - Quantity: " + quantity + " - Total Price: $" + totalPrice);
//        }
//    }

//    private Table selectOccupiedTable() {
//        List<Table> tables = tableManagement.getTables();
//
//        // Filter tables to get only occupied tables
//        List<Table> occupiedTables = tables.stream()
//                .filter(table -> table.getStatus() == Table.Status.OCCUPIED)
//                .collect(Collectors.toList());
//
//        if (occupiedTables.isEmpty()) {
//            return null;
//        }
//
//        // Display occupied tables
//        System.out.println("Occupied Tables:");
//        for (int i = 0; i < occupiedTables.size(); i++) {
//            Table table = occupiedTables.get(i);
//            System.out.println(i + 1 + ". Table ID: " + table.getTableId() + ", Table Size: " + table.getTableSize());
//        }
//
//        // Prompt the guest to select a table
//        System.out.println("Enter the number of the table you want to assign to the order:");
//        int tableNumber = scanner.nextInt();
//        scanner.nextLine(); // Consume the new line character
//
//        if (tableNumber < 1 || tableNumber > occupiedTables.size()) {
//            System.out.println("Invalid table number. Please try again.");
//            return null;
//        }
//
//        return occupiedTables.get(tableNumber - 1);
//    }






//        private void notifyStaff(Order order) {
//        List<MenuItem> items = order.getItems();
//        for (MenuItem item : items) {
//            List<String> ingredients = item.getIngredients();
//            for (String ingredient : ingredients) {
//                InventoryItem inventoryItem = InventoryManagement.getIngredients(ingredient);
//                if (inventoryItem.getQuantity() <= inventoryItem.getThreshold()) {
//                    System.out.println("Alert: Low quantity for ingredient " + ingredient);
//                }
//            }
//        }
//    }
//    private List<Order> getCompletedOrders() {
//        List<Order> completedOrders = new ArrayList<>();
//        for (Order order : activeOrders) {
//            if (orderStatus.getOrDefault(order.getOrderId(), "").equals("Order is ready!")) {
//                completedOrders.add(order);
//            }
//        }
//        return completedOrders;
//    }
//    public void clearAllOrders() {
//        activeOrders.clear();
//    }
//    public int getActiveOrderCount() {
//        return activeOrders.size();
//    }
    //??Additional methods?

    // Getter for activeOrders
//    public List<Order> getActiveOrders() {
//        return Collections.unmodifiableList(activeOrders);
//    }
//}
// going to bed now ᇂ_ᇂ