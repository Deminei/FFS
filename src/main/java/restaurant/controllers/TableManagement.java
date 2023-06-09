package restaurant.controllers;

import restaurant.models.Table;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TableManagement {
    public static OrderProcessing orderProcessing;
    public List<Table> tables;
    public static File file;

    public TableManagement(String filepath) {
        tables = new ArrayList<>();
        file = new File(filepath);
        loadTables();
    }

    // Add a new table to the list
    public void addTable(Table table) {
        tables.add(table);
        saveTables();
    }

    private void loadTables() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tableData = line.split(",");
                int tableId = Integer.parseInt(tableData[0]);
                int tableSize = Integer.parseInt(tableData[1]);
                Table.Status status = Table.Status.valueOf(tableData[2]);
                Table table = new Table(tableId, tableSize, status);
                tables.add(table);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TableManagement tableManagement = new TableManagement("C:/Users/wowin/WIN_Program/FS103/FFS/src/main/java/restaurant/utils/tables.txt");



        // Load the tables from the file
        tableManagement.loadTables();


        // Display the tables
        List<Table> tables = tableManagement.getTables();
        for (Table table : tables) {
            System.out.println(table);
        }
    }
    


    private void saveTables() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Table table : tables) {
                String line = table.getTableId() + "," + table.getTableSize() + "," + table.getStatus();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public void editTable(Table table) {
        // Find the table with the same table number and update its properties
        for (int i = 0; i < tables.size(); i++) {
            Table existingTable = tables.get(i);
            if (existingTable.getTableId() == table.getTableId()) {
                existingTable.setTableSize(table.getTableSize());
                existingTable.setStatus(String.valueOf(table.getStatus()));
                break;
            }
        }

        saveTables();
    }

    public void assignGuestToTable(Table table) {
        // Find the table with the given table ID and set its status to OCCUPIED
        for (Table existingTable : tables) {
            if (existingTable.getTableId() == table.getTableId()) {
                existingTable.setStatus(String.valueOf(Table.Status.OCCUPIED));
                break;
            }
        }

        saveTables();
    }



}


