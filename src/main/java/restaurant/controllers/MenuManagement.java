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
//  NEEDS WORK DUE TO STUPID LIST
  

//    public static void main(String[] args) {
//
//        MenuItem Coffee = new MenuItem("Coffee Abomination","Delicious tonic water coffee beverage with two shots of espresso",3,8.0, Arrays.asList("1 can of tonic water Cherry syrup 2(oz) shots of espresso"));
//        // Test the addMenuItem method
//        addMenuItem(Coffee);
//        // Print the updated menu
//        List<MenuItem> menuItems = MenuManagement.getMenuItems();
//        System.out.println("Updated Menu:");
//        for (int i = 0; i < menuItems.size(); i++) {
//            MenuItem item = menuItems.get(i);
//            System.out.println(i + 1 + ". " + item.getName() + " - " + item.getDescription() + " - $" + item.getPrice());
//        }
//    }
    public static List<MenuItem> getMenuItems() {

        List<MenuItem> menuItems = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(MENU_FILE_PATH))) {
            List<String[]> menuData = reader.readAll();
            for (String[] menuItemData : menuData) {
                MenuItem menuItem = new MenuItem(
                        menuItemData[0],
                        menuItemData[1],
                        Integer.parseInt(menuItemData[2]),
                        Double.parseDouble(menuItemData[3]),
                        Collections.singletonList(menuItemData[4])
                );

//                System.out.println(menuItem);
                menuItems.add(menuItem);

            }
            System.out.println("MenuData" + menuData);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(menuItems);
        return menuItems;
    }

    //    Needed to add files to CSV. Could not hard code it due to complex syntax
    public void addMenuItem(MenuItem item) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH, true))) {
            writer.writeNext(new String[]{MenuItem.getName(), MenuItem.getDescription(), String.valueOf(MenuItem.getPreparationTime()), String.valueOf(MenuItem.getPrice()), String.valueOf(MenuItem.getIngredients())});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
//        menuItems.removeIf(menuItem -> menuItem.getName().equals(name));
//        saveMenuItems(menuItems);

//        MenuItem item = new MenuItem(name, "", 0, 0.0, Collections.singletonList(""));
//        this.removeMenuItem(item);
    }

//    public void removeMenuItem(MenuItem item) {
//        List<MenuItem> menuItems = this.getMenuItems();
//        menuItems.removeIf(menuItem -> menuItem.getName().equals(item.getName()));
//        saveMenuItems(menuItems);
//    }

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




