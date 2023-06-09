package restaurant.controllers;

import restaurant.models.Table;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserLogin {
    public void findUser(TableManagement tableManagement, Table table, MenuManagement menu, InventoryManagement inventory,OrderProcessing orderProcessing) {
        System.out.println("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String employeeUserName = scanner.nextLine();

        System.out.println("Enter password: ");
        String employeePassword = scanner.nextLine();

        String filepath = "src/main/java/restaurant/utils/users.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean foundUser = false;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData.length >= 3 && userData[0].equals(employeeUserName)) {
                    String storedHashedPassword = userData[1];
                    if (validatePassword(employeePassword, storedHashedPassword)) {
                        foundUser = true;
                        if (userData[2].equals("STAFF")) {
                            staffOptions(tableManagement, table, orderProcessing, inventory, menu);
                        } else if (userData[2].equals("MANAGER")) {
                            managerOptions(tableManagement, table, menu, inventory, orderProcessing);
                        }
                    }
                    break; // Added to exit the loop after finding a matching user
                }
            }
            if (!foundUser) {
                System.out.println("Invalid username or password.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validatePassword(String password, String storedHashedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] hashedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString().equals(storedHashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    public void storeUserData(User user) {
//        String filepath = "src/main/java/restaurant/utils/users.txt";
//        List<User> employeeList = new ArrayList<>();
//
//        try {
//            File outputFile = new File(filepath);
//            if (outputFile.createNewFile()) {
//                System.out.println("File created: " + outputFile.getName());
//            } else {
//                System.out.println("File already exists. File will be updated.");
//            }
//
//            BufferedWriter writeUserData = new BufferedWriter(new FileWriter(filepath, true));
//
//            // Hash the password
//            String hashedPassword = hashPassword(user.getPassword());
//
//            // Write the hashed user data to the file
//            writeUserData.write(user.getUsername() + ";" + hashedPassword + ";" + user.getRole());
//            writeUserData.newLine();
//            writeUserData.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    private void backupUsersFile() {
//        String sourceFile = "src/main/java/restaurant/utils/users.txt";
//        String backupFile = "src/main/java/restaurant/utils/users_backup.txt";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                writer.write(line);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void replacePasswordsWithHashed() {
//        String sourceFile = "src/main/java/restaurant/utils/users.txt";
//        String tempFile = "src/main/java/restaurant/utils/users_temp.txt";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] userData = line.split(";");
//                if (userData.length >= 3) {
//                    String username = userData[0];
//                    String password = userData[1];
//                    String role = userData[2];
//
//                    // Hash the password
//                    String hashedPassword = hashPassword(password);
//
//                    // Write the hashed user data to the temporary file
//                    writer.write(username + ";" + hashedPassword + ";" + role);
//                } else {
//                    // Write the line as it is if it doesn't contain username, password, and role
//                    writer.write(line);
//                }
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Replace the original users.txt file with the temporary file
//        File source = new File(sourceFile);
//        File temp = new File(tempFile);
//        if (source.exists()) {
//            source.delete();
//            temp.renameTo(source);
//        }
//    }

    public void staffOptions(TableManagement tableManagement, Table table, OrderProcessing orderProcessing, InventoryManagement inventory, MenuManagement menu) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        while (running) {
            System.out.println("Enter 1 to assign guest to a table.");
            System.out.println("Enter 2 to access to place guest order.");
            System.out.println("Press 3 to check restaurant inventory.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    // Call table manager function
                    tableManagement.assignGuestToTable();
                    break;
                case 2:
//                    // Call Order function
//                    orderProcessing.placeGuestOrder(menu, table, inventory);
                    break;
                case 3:
                    inventory.checkInventory();
                    break;
                default:
                    System.out.println("Logging out. Goodbye.");
                    running = false;
                    break;
            }
        }
    }

    public void managerOptions(TableManagement tableManagement, Table table, MenuManagement menu, InventoryManagement inventory, OrderProcessing orderProcessing) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter 1 to assign guest to a table.");
            System.out.println("Enter 2 to access to place guest order.");
            System.out.println("Press 3 to manage restaurant inventory.");
            System.out.println("Press 4 to edit the menu.");
            System.out.println("Press 5 to get a sales report.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    tableManagement.assignGuestToTable();
                    break;
                case 2:
//                   Call Order function
//                    orderProcessing.placeGuestOrder();
                    break;
                case 3:
                    // Restaurant inventory function
                    inventory.manageInventory();
                    break;
                case 4:
                    // Edit menu function
                    menu.manageMenu();
                    break;
                case 5:
                    // Generate sales report
                    break;
                default:
                    System.out.println("Logging out. Goodbye.");
                    running = false;
                    break;
            }
        }
    }
}