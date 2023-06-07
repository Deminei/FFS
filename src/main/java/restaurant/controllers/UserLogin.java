package restaurant.controllers;

import restaurant.models.User;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static restaurant.controllers.InventoryManagement.inventory;
import static restaurant.controllers.InventoryManagement.addInitialIngredient;
import static restaurant.controllers.InventoryManagement.listOfIngredients;

public class UserLogin {
    public static void main(String[] args) {
        findUser();
    }

    public static void findUser() {
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
                            staffOptions();
                        } else if (userData[2].equals("MANAGER")) {
                            managerOptions();
                        }
                    }
                    break; // Added to exit the loop after finding a matching user
                }
            }
            if (!foundUser) {
                System.out.println("Invalid username or password.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validatePassword(String password, String storedHashedPassword) {
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

    public static void storeUserData(User user) {
        String filepath = "src/main/java/restaurant/utils/users.txt";
        List<User> employeeList = new ArrayList<>();

        try {
            File outputFile = new File(filepath);
            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File already exists. File will be updated.");
            }

            BufferedWriter writeUserData = new BufferedWriter(new FileWriter(filepath, true));

            // Hash the password
            String hashedPassword = hashPassword(user.getPassword());

            // Write the hashed user data to the file
            writeUserData.write(user.getUsername() + ";" + hashedPassword + ";" + user.getRole());
            writeUserData.newLine();
            writeUserData.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String hashPassword(String password) {
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

    private static void backupUsersFile() {
        String sourceFile = "src/main/java/restaurant/utils/users.txt";
        String backupFile = "src/main/java/restaurant/utils/users_backup.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void replacePasswordsWithHashed() {
        String sourceFile = "src/main/java/restaurant/utils/users.txt";
        String tempFile = "src/main/java/restaurant/utils/users_temp.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData.length >= 3) {
                    String username = userData[0];
                    String password = userData[1];
                    String role = userData[2];

                    // Hash the password
                    String hashedPassword = hashPassword(password);

                    // Write the hashed user data to the temporary file
                    writer.write(username + ";" + hashedPassword + ";" + role);
                } else {
                    // Write the line as it is if it doesn't contain username, password, and role
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Replace the original users.txt file with the temporary file
        File source = new File(sourceFile);
        File temp = new File(tempFile);
        if (source.exists()) {
            source.delete();
            temp.renameTo(source);
        }
    }

    public static void staffOptions() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        TableManagement tableManagement = new TableManagement();
        InventoryManagement inventory = new InventoryManagement();

        addInitialIngredient("8oz Coffee Abomination", 50, 5);
        addInitialIngredient("8oz Oat Milk Latte", 50, 5);
        addInitialIngredient("8oz Almond Milk Latte", 50, 5);
        addInitialIngredient("8oz Skim Milk Latte", 50, 3);
        addInitialIngredient("Oatmeal", 35, 4);
        addInitialIngredient("Bacon BreakFast Sandwich", 30, 2);
        addInitialIngredient("Turkey Sandwich", 30, 2);
        addInitialIngredient("Ham Sandwich", 30, 2);

        while (running) {
            System.out.println("Enter 1 to assign guest to a table.");
            System.out.println("Enter 2 to access to place guest order.");
            System.out.println("Press 3 to check restaurant inventory.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    // Call table manager function
                    assignGuestToTable(tableManagement);
                    break;
                case 2:
                    // Call Order function
                    OrderProcessing.placeGuestOrder(new OrderProcessing());
                    break;
                case 3:
                    System.out.println(listOfIngredients);
                    inventory.checkInventory();
                    break;
                default:
                    System.out.println("Logging out. Goodbye.");
                    running = false;
                    break;
            }
        }
    }

    public static void managerOptions() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // Instantiate the TableManager class
        TableManagement tableManagement = new TableManagement();
        InventoryManagement inventory = new InventoryManagement();

        addInitialIngredient("8oz Coffee Abomination", 50, 5);
        addInitialIngredient("8oz Oat Milk Latte", 50, 5);
        addInitialIngredient("8oz Almond Milk Latte", 50, 5);
        addInitialIngredient("8oz Skim Milk Latte", 50, 3);
        addInitialIngredient("Oatmeal", 35, 4);
        addInitialIngredient("Bacon BreakFast Sandwich", 30, 2);
        addInitialIngredient("Turkey Sandwich", 30, 2);
        addInitialIngredient("Ham Sandwich", 30, 2);

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
                    assignGuestToTable(tableManagement);
                    break;
                case 2:
                    // Call Order function
                    OrderProcessing.placeGuestOrder(new OrderProcessing());
                    break;
                case 3:
                    // Restaurant inventory function
                    InventoryManagement.manageInventory(inventory);
                    break;
                case 4:
                    // Edit menu function
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
    public static void assignGuestToTable(TableManagement tableManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the party size: ");
        int partySize = Integer.parseInt(scanner.nextLine());

        tableManagement.assignGuestToTable(partySize);
    }
}
