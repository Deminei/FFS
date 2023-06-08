package restaurant;

import java.util.*;
import java.util.Scanner;

import restaurant.controllers.*;
import restaurant.models.MenuItem;

import restaurant.utils.ConsoleColors;//This file is located in utils

import static restaurant.controllers.MenuManagement.*;


public class App {

    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
//      Instantiates Control classes
        TableManagement tableManagement = new TableManagement();
        InventoryManagement inventory = new InventoryManagement();
        MenuManagement menu = new MenuManagement();
        OrderProcessing orderProcessing = new OrderProcessing();
        UserLogin userLogin = new UserLogin();
//        SalesReport report = new SalesReport();


//        Runs findUser function that calls all other functions
        userLogin.findUser(tableManagement, menu, inventory, orderProcessing);

//        Populates Menu
//        menu.addMenuItem(new MenuItem("8oz Coffee Abomination", "Delicious tonic water, coffee beverage with a shot of espresso",3, 8.00, Arrays.asList("Tonic water", "Cherry syrup", "Espresso")));
//        menu.addMenuItem(new MenuItem("8oz Oat Milk Latte", "Shots of espresso served over ice with oat milk", 2, 6.00, Arrays.asList("Espresso", "Oat milk", "Ice")));
//        menu.addMenuItem(new MenuItem("8oz Almond Milk Latte", "Shots of espresso served over ice with almond milk",2, 6.00, Arrays.asList("Espresso", "Almond milk", "Ice")));
//        menu.addMenuItem(new MenuItem("Oatmeal", "Healthy Oatmeal topped with blueberries, honey, and brown sugar",5, 5.00, Arrays.asList("Oatmeal Package", "Blueberry Package", "Honey Package", "Brown Sugar Package")));
//        menu.addMenuItem(new MenuItem("Bacon BreakFast Sandwich", "Amazing bacon breakFast sandwich with egg, cheese on english muffin",8, 8.00, Arrays.asList("Bacon", "Egg", "Cheese", "English Muffin")));
//        menu.addMenuItem(new MenuItem("Turkey Sandwich", "Delicious turkey sandwich with cheese, lettuce on whole wheat",8, 10.00, Arrays.asList("Turkey", "Cheese", "Lettuce", "Whole Wheat")));
//        menu.addMenuItem(new MenuItem("Ham Sandwich", "Yummy ham sandwich served with lettuce, mayonnaise, cheese on whole wheat",3, 10.00, Arrays.asList("Ham","Lettuce", "Mayonnaise", "Cheese", "Whole Wheat")));

////        Adds ingredients to inventory
//        inventory.addInitialIngredient("8oz Coffee Abomination", 50, 5);
//        inventory.addInitialIngredient("8oz Oat Milk Latte", 50, 5);
//        inventory.addInitialIngredient("8oz Almond Milk Latte", 50, 5);
//        inventory.addInitialIngredient("8oz Skim Milk Latte", 50, 3);
//        inventory.addInitialIngredient("Oatmeal", 35, 4);
//        inventory.addInitialIngredient("Bacon BreakFast Sandwich", 30, 2);
//        inventory.addInitialIngredient("Turkey Sandwich", 30, 2);
//        inventory.addInitialIngredient("Ham Sandwich", 30, 2);

//        Adds tables to List<Table>
//        tableManagement.addTable();


//        Testing individual functions
//        inventory.manageInventory(inventory);
//        menu.addNewMenuItem();
//        menu.removeMenuItem();

    }



}
