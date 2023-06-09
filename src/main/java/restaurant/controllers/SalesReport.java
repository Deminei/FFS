package restaurant.controllers;
import restaurant.models.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SalesReport{
    private double dailyRevenue;

    public SalesReport(User user, OrderProcessing orderProcessing, MenuManagement menu) {
        this.dailyRevenue = 0.0;
    }

    private String getFormattedCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return (dtf.format(now)).toString();
    }

        public void generateDailySalesReport(User user, OrderProcessing orders, MenuManagement menu) {
        if (user.getRole() == User.Role.MANAGER) {
            double totalRevenue = calculateTotalRevenue(orders);
            List<String> popularItems = findPopularItems(orders, menu);
            Map<Integer, Double> tableSales = findTablesWithMostSales();

            // Update daily revenue with the total revenue
            dailyRevenue += totalRevenue;

            Sales sales = new Sales(totalRevenue, popularItems, (List<Integer>) tableSales);
            exportSalesReport(sales);
        } else {
            System.out.println("Access denied. You do not have the required permission to view and export the sales report.");
        }
    }

        private void exportSalesReport(Sales sales) {
        // Get the current date
//        Date currentDate = new Date();

        // Format the date for the file name
        String fileName = getFormattedCurrentDate() + ".txt";

        // Define the file path
        String filePath = "reports/" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the sales report content
            writer.write("Sales Report - " + getFormattedCurrentDate());
            writer.newLine();
            writer.newLine();

            // Write the total revenue
            writer.write("Total Revenue: $" + formatDecimal(sales.getTotalRevenue()));
            writer.newLine();
            writer.newLine();

            // Write the most popular items
            writer.write("Most Popular Items:");
            writer.newLine();
            for (String item : sales.getPopularItems()) {
                writer.write("- " + item);
                writer.newLine();
            }
            writer.newLine();

            // Write the tables with the most orders
            writer.write("Tables with Most Orders:");
            writer.newLine();
            for (Integer tableId : sales.getTablesWithMostOrders()) {
                writer.write("- Table " + tableId);
                writer.newLine();
            }
            writer.newLine();

            // Print a success message
            System.out.println("Sales report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating sales report: " + e.getMessage());
        }
    }


    private String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }

    private double calculateTotalRevenue(OrderProcessing orders) {
        double totalRevenue = 0;
        for (Order order : orders.getCompletedOrders()) {
            totalRevenue += order.getTotalPrice();
        }
        return totalRevenue;
    }

    public List<String> findPopularItems(OrderProcessing orderprocessing, MenuManagement menu) {
        Map<String, Integer> itemFrequency = new HashMap<>();
        List<MenuItem> timesOrdered = new ArrayList<>();

        for (Order order : orderprocessing.getCompletedOrders()) {
            for (MenuItem item : order.getItems()) {
//                MenuItem menuItem = item..getMenuItem();
                String itemName = item.getName();
//                get frequency of item


                itemFrequency.put(itemName, itemFrequency.getOrDefault(itemName, 0));
            }
        }

        // Find the most popular items
        int maxFrequency = 0;
        List<String> popularItems = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : itemFrequency.entrySet()) {
            int frequency = entry.getValue();
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                popularItems.clear();
                popularItems.add(entry.getKey());
            } else if (frequency == maxFrequency) {
                popularItems.add(entry.getKey());
            }
        }

        // Print the most popular items
        if (popularItems.isEmpty()) {
            System.out.println("No popular items found.");
        } else {
            System.out.println("Most popular item(s):");
            for (String itemName : popularItems) {
                System.out.println(itemName);
            }
        }
        return popularItems;
    }

        private Map<Integer, Double> findTablesWithMostSales() {
        Map<Integer, Double> tableSalesMap = new HashMap<>();

        // Calculate the sales totals for each table
//        for (Order order : orders.getItem) {
//            int tableId = order.getTable().getTableId();
//            double totalPrice = order.getTotalPrice();
//
//            // Update the sales total for the table
//            tableSalesMap.put(tableId, tableSalesMap.getOrDefault(tableId, 0.0) + totalPrice);
//        }

        // Sort the table-sales entries based on sales totals (descending order)
        List<Map.Entry<Integer, Double>> sortedTableSales = new ArrayList<>(tableSalesMap.entrySet());
        sortedTableSales.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Retrieve the tables with the most sales and their sales totals
        Map<Integer, Double> tablesWithMostSales = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<Integer, Double> entry : sortedTableSales) {
            tablesWithMostSales.put(entry.getKey(), entry.getValue());
            count++;

            // Break the loop after retrieving the top 3 tables
            if (count == 3) {
                break;
            }
        }

        return tablesWithMostSales;
    }



}
//public class SalesReport {
//    private List<Order> orders;
//    private List<Table> tables;
//    private User user;
//    private double dailyRevenue; // Track daily revenue
//
//    public SalesReport(List<Order> orders, List<Table> tables, User user) {
//        this.orders = orders;
//        this.tables = tables;
//        this.user = user;
//        this.dailyRevenue = 0.0; // Initialize daily revenue as 0
//    }
//
//    private String getFormattedDate(Date date) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(date);
//    }
//    private String formatDecimal(double value) {
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        return decimalFormat.format(value);
//    }
//
//    public void generateDailySalesReport(List<Order> completedOrders) {
//    }
//
//    public void generateDailySalesReport() {
//        if (user.getRole() == User.Role.MANAGER) {
//            double totalRevenue = calculateTotalRevenue();
//            List<String> popularItems = findPopularItems(orders);
//            Map<Integer, Double> tableSales = findTablesWithMostSales();
//
//            // Update daily revenue with the total revenue
//            dailyRevenue += totalRevenue;
//
//            Sales sales = new Sales(totalRevenue, popularItems, (List<Integer>) tableSales);
//            exportSalesReport(sales);
//        } else {
//            System.out.println("Access denied. You do not have the required permission to view and export the sales report.");
//        }
//    }
//
//
//

//
//
//
//
//
//
//    private void exportSalesReport(Sales sales) {
//        // Get the current date
//        Date currentDate = new Date();
//
//        // Format the date for the file name
//        String fileName = getFormattedDate(currentDate) + ".txt";
//
//        // Define the file path
//        String filePath = "reports/" + fileName;
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            // Write the sales report content
//            writer.write("Sales Report - " + getFormattedDate(currentDate));
//            writer.newLine();
//            writer.newLine();
//
//            // Write the total revenue
//            writer.write("Total Revenue: $" + formatDecimal(sales.getTotalRevenue()));
//            writer.newLine();
//            writer.newLine();
//
//            // Write the most popular items
//            writer.write("Most Popular Items:");
//            writer.newLine();
//            for (String item : sales.getPopularItems()) {
//                writer.write("- " + item);
//                writer.newLine();
//            }
//            writer.newLine();
//
//            // Write the tables with the most orders
//            writer.write("Tables with Most Orders:");
//            writer.newLine();
//            for (Integer tableId : sales.getTablesWithMostOrders()) {
//                writer.write("- Table " + tableId);
//                writer.newLine();
//            }
//            writer.newLine();
//
//            // Print a success message
//            System.out.println("Sales report generated successfully.");
//        } catch (IOException e) {
//            System.out.println("Error generating sales report: " + e.getMessage());
//        }
//    }
//
//
//
//    // Rest of the code...
//}

// I'm so tired rn ಥ‿ಥ