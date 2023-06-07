package restaurant.controllers;

import restaurant.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableManagement {
    private List<Table> tables;

    public TableManagement() {
        tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void removeTable(Table table) {
        tables.remove(table);
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Table> getAvailableTables(int partySize) {
        List<Table> availableTables = new ArrayList<>();
        for (Table table : tables) {
            if (table.getStatus() == Table.Status.AVAILABLE && table.getTableSize() >= partySize) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    public void assignGuestToTable(int partySize) {
        Table table1 = new Table(1, 4, Table.Status.AVAILABLE); // Create a table with ID 1 and size 4
        Table table2 = new Table(2, 6, Table.Status.AVAILABLE); // Create a table with ID 2 and size 6
        Table table3 = new Table(3,2, Table.Status.AVAILABLE);

        tables.add(table1); // Add table1 to the tables list
        tables.add(table2); // Add table2 to the tables list
        tables.add(table3);
        Scanner scanner = new Scanner(System.in);

        List<Table> availableTables = getAvailableTables(partySize);

        if (availableTables.isEmpty()) {
            System.out.println("No available tables for the given party size.");
        } else {
            System.out.println("Available Tables:");
            for (Table table : availableTables) {
                System.out.println("Table " + table.getTableId() + ": seats: " + table.getTableSize());
            }

            System.out.println("Enter the table ID to assign the guest: ");
            int tableId = Integer.parseInt(scanner.nextLine());

            Table selectedTable = null;
            for (Table table : availableTables) {
                if (table.getTableId() == tableId) {
                    selectedTable = table;
                    break;
                }
            }

            if (selectedTable != null) {
                assignTableToCustomer(selectedTable, Table.Status.OCCUPIED);
                System.out.println("Guests assigned to Table " + tableId);
            } else {
                System.out.println("Invalid table ID.");
            }
        }
    }

    public void assignTableToCustomer(Table table, Table.Status status) {
        table.setStatus(String.valueOf(status));
    }

    public void freeTable(Table table) {
        table.setStatus(String.valueOf(Table.Status.AVAILABLE));
    }

    public static void main(String[] args) {
        // Example usage
        TableManagement tableManagement = new TableManagement();

        // Create tables
        Table table1 = new Table(1, 4, Table.Status.AVAILABLE);
        Table table2 = new Table(2, 6, Table.Status.RESERVED);
        Table table3 = new Table(3, 2, Table.Status.OCCUPIED);

        // Add tables to management
        tableManagement.addTable(table1);
        tableManagement.addTable(table2);
        tableManagement.addTable(table3);

        // Get available tables for a party size
        List<Table> availableTables = tableManagement.getAvailableTables(4);
        System.out.println("Available tables for party size 4: " + availableTables);

        // Free a table
        tableManagement.freeTable(table1);

        // Get all tables
        List<Table> allTables = tableManagement.getTables();
        System.out.println("All tables: " + allTables);
    }
}
