package restaurant.controllers;
import restaurant.models.*;
import restaurant.utils.ConsoleColors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReport {
    private List<Order> orders;
    private List<Table> tables;
    private User user;
    private InventoryManagement inventoryManagement;
    private MenuManagement menuManagement;

    public SalesReport(List<Order> orders, List<Table> tables, User user) {
        this.orders = orders;
        this.tables = tables;
        this.user = user;
        this.inventoryManagement = new InventoryManagement();
        this.menuManagement = new MenuManagement();
    }

    public void generateDailySalesReport() {
        // Check if the user has the role of "manager"
        if (user.getRole() == User.Role.MANAGER) {
            // Generate the sales report data
            double totalRevenue = calculateTotalRevenue();
            List<String> popularItems = findPopularItems();
            List<Integer> tablesWithMostOrders = findTablesWithMostOrders();

            // Create a Sales object with the generated data
            Sales sales = new Sales(totalRevenue, popularItems, tablesWithMostOrders);

            // Export the sales report
            exportSalesReport(sales);
        } else {
            System.out.println("Access denied. You do not have the required permission to view and export the sales report.");
        }
    }

    private double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue += order.getTotalPrice();
        }
        return totalRevenue;
    }

    private List<String> findPopularItems() {
        // Implement this method using the MenuManagement class
        List<String> popularItems = menuManagement.getPopularItems();
        return popularItems;
    }

    private List<Integer> findTablesWithMostOrders() {
        // Implement this method using the TableManagement class
        List<Integer> tablesWithMostOrders = tableManagement.getTablesWithMostOrders();
        return tablesWithMostOrders;
    }

    private void exportSalesReport(Sales sales) {
        Date currentDate = new Date();
        String fileName = getFormattedDate(currentDate) + ".txt";
        String filePath = "reports/" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Writes the sales report content
            writer.write("Daily Sales Report" + ConsoleColors.BLUE_BOLD_BRIGHT + "\nDate: " + getFormattedDate(currentDate) + ConsoleColors.RESET);
            writer.newLine();
            writer.newLine();
            writer.write("Total Revenue: $" + formatDecimal(sales.getTotalRevenue()));
            writer.newLine();
            writer.newLine();
            writer.write("Most Popular Items:");
            writer.newLine();
            for (String item : sales.getPopularItems()) {
                writer.write("- " + item);
                writer.newLine();
            }
            writer.newLine();
            writer.write("Tables with Most Orders:");
            writer.newLine();
            for (Integer tableId : sales.getTablesWithMostOrders()) {
                writer.write("- Table " + tableId);
                writer.newLine();
            }
            writer.newLine();

            System.out.println("Sales report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating sales report: " + e.getMessage());
        }
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }
}
// I'm so tired rn ಥ‿ಥ