package restaurant.controllers;

import restaurant.models.InventoryItem;
import restaurant.models.MenuItem;
import restaurant.models.Table;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TableManagement{
    public List<Table> tables;

    public TableManagement(){
        tables = new ArrayList<>();
    }

    public void addTableToRestaurant(int tableID, int tableSize, Table.Status tableStatus ){
        tables.add(new Table( tableID,  tableSize, tableStatus));
    }

    public void checkTables() {
        for (Table table : tables) {
            System.out.println("Table " + table.getTableId() + ": " + table.getTableSize() + "seats " + "Status: " + table.getStatus());
//            System.out.println(tables);

        }
    }

    public void editTable() {
        Scanner scanner = new Scanner(System.in);
        boolean editing = true;
        while (editing) {

            System.out.println("Press 1 to edit table status.");
            System.out.println("Press 0 to exit.");

            int userselected = Integer.parseInt(scanner.nextLine());

            switch (userselected) {
                case 1:

                            boolean running = true;

                            while (running) {
                                System.out.println("What is the ID of the table you would like to edit?");
                                System.out.println("Press 0 to exit");

                                int tableIDNumber = Integer.parseInt(scanner.nextLine());


                                if (tableIDNumber == 0){
                                    break;
                                }

                                for (Table table : tables) {

                                    if (table.getTableId() == tableIDNumber) {
                                System.out.println("Enter 1 change table status.");
                                System.out.println("Enter 0 to exit.");

                                int optionSelected = Integer.parseInt(scanner.nextLine());

                                switch (optionSelected) {
                                    case 1:
                                        System.out.println("Enter the new status of the table. ");
                                        String newStatus = scanner.nextLine();
                                        table.setStatus(newStatus);
                                        break;
                                    default:
                                        System.out.println("Logging out. Goodbye.");
                                        running = false;
                                        break;
                                }
                            }
                        }
                    }default:
                    System.out.println("Logging out. Goodbye.");
                    editing = false;
                    break;

            }
        }
    }

    public void assignGuestToTable() {
        // Find the table with the given table ID and set its status to OCCUPIED
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the party size: ");
        int partySize = Integer.parseInt(scanner.nextLine());

        List<Table> guestWillFit = new ArrayList<>();

        for (Table table : tables) {
            if (table.getTableSize() >= partySize) {
                guestWillFit.add(table);
            }
        }

        for (Table table : guestWillFit) {
            System.out.println(table.getTableId() + ": " + table.getTableSize() + " " + table.getStatus());
            System.out.println(tables);
        }

        editTable();
    }
}

//public class TableManagement {
////    public OrderProcessing orderProcessing;
//    public List<Table> tables;
//    public static File file;
//
//    public TableManagement(String filepath) {
//        tables = new ArrayList<>();
//        file = new File(filepath);
//        loadTables();
//    }
//
//    // Add a new table to the list
//    public void addTable(Table table) {
//        tables.add(table);
//        saveTables();
//    }
//
//    private void loadTables() {
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] tableData = line.split(",");
//                int tableId = Integer.parseInt(tableData[0]);
//                int tableSize = Integer.parseInt(tableData[1]);
//                Table.Status status = Table.Status.valueOf(tableData[2]);
//                Table table = new Table(tableId, tableSize, status);
//                tables.add(table);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        TableManagement tableManagement = new TableManagement("src/main/java/restaurant/utils/tables.txt");
//
//
//
//        // Load the tables from the file
//        tableManagement.loadTables();
//
//
//        // Display the tables
//        List<Table> tables = tableManagement.getTables();
//        for (Table table : tables) {
//            System.out.println(table);
//        }
//    }
//
//
//
//    private void saveTables() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (Table table : tables) {
//                String line = table.getTableId() + "," + table.getTableSize() + "," + table.getStatus();
//                writer.write(line);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Table> getTables() {
//        return tables;
//    }
//
//    public void editTable(Table table) {
//        // Find the table with the same table number and update its properties
//        for (int i = 0; i < tables.size(); i++) {
//            Table existingTable = tables.get(i);
//            if (existingTable.getTableId() == table.getTableId()) {
//                existingTable.setTableSize(table.getTableSize());
//                existingTable.setStatus(String.valueOf(table.getStatus()));
//                break;
//            }
//        }
//        saveTables();
//    }
//
//    public void assignGuestToTable(Table table) {
//        // Find the table with the given table ID and set its status to OCCUPIED
//        for (Table existingTable : tables) {
//            if (existingTable.getTableId() == table.getTableId()) {
//                existingTable.setStatus(String.valueOf(Table.Status.OCCUPIED));
//                break;
//            }
//        }
//        saveTables();
//    }
//}
//

