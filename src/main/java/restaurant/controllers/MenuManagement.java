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
                menuItems.add(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return menuItems;
    }
    public static void addMenuItem(MenuItem coffee) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH, true))) {
            writer.writeNext(new String[]{MenuItem.getName(), MenuItem.getDescription(), String.valueOf(MenuItem.getPreparationTime()), String.valueOf(MenuItem.getPrice()), String.valueOf(MenuItem.getIngredients())});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeMenuItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the menu item to remove:");
        String name = scanner.nextLine();

        MenuItem item = new MenuItem(name, "", 0, 0.0, Collections.singletonList(""));
        removeMenuItem(item);
    }

    public static void removeMenuItem(MenuItem item) {
        List<MenuItem> menuItems = getMenuItems();
        menuItems.removeIf(menuItem -> menuItem.getName().equals(item.getName()));
        saveMenuItems(menuItems);
    }

    public static void editMenuItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the menu item to edit:");
        String name = scanner.nextLine();

        System.out.println("Enter the new name of the menu item:");
        String newName = scanner.nextLine();

        System.out.println("Enter the new description of the menu item:");
        String newDescription = scanner.nextLine();

        System.out.println("Enter the new preparation time (in minutes) of the menu item:");
        int newPreparationTime = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the new price of the menu item:");
        double newPrice = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the new ingredients of the menu item (separated by commas):");
        String newIngredients = scanner.nextLine();

        MenuItem newItem = new MenuItem(newName, newDescription, newPreparationTime, newPrice, Collections.singletonList(newIngredients));
        MenuItem item = new MenuItem(name, "", 0, 0.0, Collections.singletonList(""));
        editMenuItem(item, newItem);
    }
    public static void editMenuItem(MenuItem item, MenuItem newItem) {
        List<MenuItem> menuItems = getMenuItems();
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equals(item.getName())) {
                menuItem.setName(newItem.getName());
                menuItem.setDescription(newItem.getDescription());
                menuItem.setPreparationTime(newItem.getPreparationTime());
                menuItem.setPrice(newItem.getPrice());
                menuItem.setIngredients(newItem.getIngredients());
                break;
            }
        }
        saveMenuItems(menuItems);
    }

    private static void saveMenuItems(List<MenuItem> menuItems) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(MENU_FILE_PATH))) {
            for (MenuItem menuItem : menuItems) {
                writer.writeNext(new String[]{menuItem.getName(), menuItem.getDescription(), String.valueOf(menuItem.getPreparationTime()), String.valueOf(menuItem.getPrice()), menuItem.getIngredients().toString()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private static File file = new File("./menutestcsv.csv");
//
//
//    public List<String[]> fileReader(){
//        try {
//            FileReader outputReader = new FileReader(file);
//            CSVReader reader = new CSVReader(outputReader);
//            menuData = reader.readAll();
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return menuData;
//    }
//    public void fileWriter() {
//        try {
//            FileWriter outputFile = new FileWriter(file);
//            CSVWriter writer = new CSVWriter(outputFile, '|', CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
//            writer.writeAll(menuData);
//            writer.close();
//            } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void add(MenuItem item){
//        menuData.add((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()), Double.toString(item.getPrice()), item.getIngredients().toString()}));
//        fileWriter();
//    }
//   public void delete(MenuItem item){
//       menuData.remove((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()),String.valueOf(item.getPrice()), item.getIngredients().toString()}));
//    fileWriter();
//   }
//   public void edit(MenuItem item){
//    boolean running = true;
//    while(running){
//        int option = prompt("Pick from the list of options below", new String[]{
//            "update the item's name",
//            "update the item's description",
//            "update the item's preparation time",
//            "update the item's price",
//            "Update the item's ingredients"
//    });
//
//    switch(option){
//        case 1:
//        item.setName(prompt("What's the new name?"));
//        break;
//        case 2: item.setDescription(prompt("What would you like the new description to say?"));
//        break;
//        case 3: item.setPreparationTime(Integer.parseInt((prompt("What is the new preparation time?"))));
//        break;
//        case 4: item.setPrice(Double.parseDouble((prompt("What is the new price? EX:0.00"))));
//        break;
//        case 5: item.setIngredients(Arrays.asList(prompt("What are the new ingredients?").split("|")));
//        break;
//        default:
//        System.out.println("Logging out. Goodbye!");
//        running = false;
//        break;
//    }
//    }
//   }
//public int prompt(String question, String[] options){
//    System.out.println(question);
//    for(int i = 0; i < options.length; i++){
//        System.out.println(i + ": " + options[i]);
//    }
//    String input = prompt("");
//    int parsedInput = Integer.parseInt(input);
//    return parsedInput;
//}
//public String prompt(String question){
//    System.out.println(question);
//    Scanner scanner = new Scanner(System.in);
//    String input = scanner.nextLine();
//    return input;
//}

        // menuData.add(new MenuItem("8oz Coffee Abomination", "Delicious tonic water, coffee beverage with a shot of espresso",3, 8.00, Arrays.asList("Tonic water", "Cherry syrup", "Espresso")));
        // menuData.add(new MenuItem("8oz Oat Milk Latte", "Shots of espresso served over ice with oat milk", 2, 6.00, Arrays.asList("Espresso", "Oat milk", "Ice")));
        // menuData.add(new MenuItem("8oz Almond Milk Latte", "Shots of espresso served over ice with almond milk",2, 6.00, Arrays.asList("Espresso", "Almond milk", "Ice")));
        // menuData.add(new MenuItem("Oatmeal", "Heathly Oatmeal topped with blueberries, honey, and brown sugar",5, 5.00, Arrays.asList("Oatmeal Package", "Blueberry Package", "Honey Package", "Brown Sugar Package")));
        // menuData.add(new MenuItem("Bacon BreakFast Sandwich", "Amazing bacon breakFast sandwich with egg, cheese on english muffin",8, 8.00, Arrays.asList("Bacon", "Egg", "Cheese", "English Muffin")));
        // menuData.add(new MenuItem("Turkey Sandwich", "Delicious turkey sandwich with cheese, lettuce on whole wheat",8, 10.00, Arrays.asList("Turkey", "Cheese", "Lettuce", "Whole Wheat")));
        // menuData.add(new MenuItem("Ham Sandwich", "Yummy ham sandwich served with lettuce, mayonnaise, cheese on whole wheat",3, 10.00, Arrays.asList("Ham","Lettuce", "Mayonnaise", "Cheese", "Whole Wheat")));

    }

