package restaurant.controllers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import com.opencsv.exceptions.CsvException;
import restaurant.models.MenuItem;


public class MenuManagement {
    private static final String MENU_FILE_PATH = "./target/menu.csv";

    public MenuManagement() {
    }

    //  Creates an array of menu items from csv
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        List<String[]> menuData;
//
        try (CSVReader reader = new CSVReader(new FileReader(MENU_FILE_PATH))) {
            menuData = reader.readAll();

            MenuItem menuItem = null;

            for (String[] arrays : menuData) {

                String name = arrays[0];
                String description = arrays[1];
                int preptime = Integer.parseInt(arrays[2]);
                double price = Double.parseDouble(arrays[3]);
                String ingredients = arrays[4];

                menuItem = new MenuItem(name, description, preptime, price, Collections.singletonList(ingredients));
                menuItems.add(menuItem);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        System.out.println(menuItems);
        return menuItems;
    }

    //    Needed to add files to CSV. Could not hard code it due to complex syntax
//    public void addMenuItem(MenuItem item) {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH, true))) {
//            writer.writeNext(new String[]{MenuItem.getName(), MenuItem.getDescription(), String.valueOf(MenuItem.getPreparationTime()), String.valueOf(MenuItem.getPrice()), String.valueOf(MenuItem.getIngredients())});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void addNewMenuItem() {
        Scanner scanner = new Scanner(System.in);
//        String name, String description, int preparationTime, double price, List<String> ingredients
        System.out.println("Enter the name of the new menu item:");
        String name = scanner.nextLine();

        System.out.println("Enter the description of the new menu item:");
        String description = scanner.nextLine();

        System.out.println("Enter the preparation time (in minutes) of the new menu item:");
        int preparationTime = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the price of the new menu item:");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the ingredients of the new menu item (separated by commas):");
        String ingredients = scanner.nextLine();

        MenuItem item = new MenuItem(name, description, preparationTime, price, Collections.singletonList(ingredients));

        try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH, true))) {
            writer.writeNext(new String[]{item.getName(), item.getDescription(), String.valueOf(item.getPreparationTime()), String.valueOf(item.getPrice()), String.valueOf(item.getIngredients())});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    NEEDS WORK DUE TO STUPID LIST
    public void removeMenuItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the menu item to remove:");
        String name = scanner.nextLine();

        List<MenuItem> menuItems = this.getMenuItems();
        System.out.println(menuItems);
        menuItems.removeIf(menuItem -> menuItem.getName().equals(name));
//        saveMenuItems(menuItems);

    }

    public void removeMenuItem(MenuItem item) {
        List<MenuItem> menuItems = this.getMenuItems();
        menuItems.removeIf(menuItem -> menuItem.getName().equals(item.getName()));
//        saveMenuItems(menuItems);
    }

    public void editMenuItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which menu item would you like to edit?");
        String name = scanner.nextLine();

        List<MenuItem> menuItems = this.getMenuItems();
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equals(name)) {

                boolean running = true;

                while (running) {
                    System.out.println("Enter 1 to edit item name.");
                    System.out.println("Enter 2 to edit item description.");
                    System.out.println("Press 3 to edit item preparation time.");
                    System.out.println("Press 4 to edit item price.");
                    System.out.println("Press 5 to edit item ingredients.");
                    System.out.println("Enter 0 to exit.");

                    int optionSelected = Integer.valueOf(scanner.nextLine());

                    switch (optionSelected) {
                        case 1:
                            System.out.println("Enter the new name of the item. ");
                            String newName = scanner.nextLine();
                            menuItem.setName(newName);
                            break;
                        case 2:
                            System.out.println("Enter the new description of the item. ");
                            String newDescription = scanner.nextLine();
                            menuItem.setDescription(newDescription);
                            break;
                        case 3:
                            System.out.println("Enter the new preparation time of the item. ");
                            int newPrepTime = Integer.valueOf(scanner.nextLine());
                            menuItem.setPreparationTime(newPrepTime);
                            break;
                        case 4:
                            System.out.println("Enter the new price of the item. ");
                            double newPrice = Double.valueOf(scanner.nextLine());
                            menuItem.setPrice(newPrice);
                            break;
                        case 5:
                            System.out.println("Enter the new ingredients of the menu item (separated by commas):");
                            String newIngredients = scanner.nextLine();
                            menuItem.setIngredients(Collections.singletonList(newIngredients));
                            break;
                        default:
                            System.out.println("Logging out. Goodbye.");
                            running = false;
                            break;
                    }
                }
            }
        }
    }

        private void saveMenuItems(){
            List<MenuItem> menuItems = this.getMenuItems();

            try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH))) {
                for (MenuItem menuItem : menuItems) {
                    writer.writeNext(new String[]{menuItem.getName(), menuItem.getDescription(), String.valueOf(menuItem.getPreparationTime()), String.valueOf(menuItem.getPrice()), menuItem.getIngredients().toString()});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public void manageMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("What would you like to do with the menu?");
            System.out.println("Enter 1 to view the menu.");
            System.out.println("Enter 2 to add an item.");
            System.out.println("Press 3 to remove an item.");
            System.out.println("Press 4 to edit an item.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    getMenuItems();
                    break;
                case 2:
                    addNewMenuItem();
                    break;
                case 3:
                    removeMenuItem();
                    break;
                case 4:
                    editMenuItem();
                    break;
                default:
                    System.out.println("Leaving menu management. Goodbye.");
                    running = false;
                    break;
            }
        }
    }
}




