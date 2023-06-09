package restaurant;

import restaurant.controllers.*;
import restaurant.models.*;
import java.util.Arrays;

import static restaurant.models.Table.Status.*;

public class App {

    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
//      Instantiates Control classes
        TableManagement table = new TableManagement();
        InventoryManagement inventory = new InventoryManagement();
        MenuManagement menu = new MenuManagement();
        OrderProcessing orderProcessing = new OrderProcessing();
        UserLogin userLogin = new UserLogin();
//        SalesReport report = new SalesReport();


        orderProcessing.placingOrder(table, menu, inventory);

//        Runs findUser function that calls all other functions
//        userLogin.findUser(table, menu, inventory, orderProcessing);

//        Populate list of tables
        table.addTableToRestaurant(1,4, AVAILABLE);
        table.addTableToRestaurant(2,6, AVAILABLE);
        table.addTableToRestaurant(3,2, AVAILABLE);
        table.addTableToRestaurant(4,4, AVAILABLE);
//
//        table.assignGuestToTable();
//        table.checkTables();

//        Populates Menu
//        menu.addMenuItem(new MenuItem("8oz Coffee Abomination", "Delicious tonic water, coffee beverage with a shot of espresso",3, 8.00, Arrays.asList("Tonic water", "Cherry syrup", "Espresso")));
//        menu.addMenuItem(new MenuItem("8oz Oat Milk Latte", "Shots of espresso served over ice with oat milk", 2, 6.00, Arrays.asList("Espresso", "Oat milk", "Ice")));
//        menu.addMenuItem(new MenuItem("8oz Almond Milk Latte", "Shots of espresso served over ice with almond milk",2, 6.00, Arrays.asList("Espresso", "Almond milk", "Ice")));
//        menu.addMenuItem(new MenuItem("Oatmeal", "Healthy Oatmeal topped with blueberries, honey, and brown sugar",5, 5.00, Arrays.asList("Oatmeal Package", "Blueberry Package", "Honey Package", "Brown Sugar Package")));
//        menu.addMenuItem(new MenuItem("Bacon BreakFast Sandwich", "Amazing bacon breakFast sandwich with egg, cheese on english muffin",8, 8.00, Arrays.asList("Bacon", "Egg", "Cheese", "English Muffin")));
//        menu.addMenuItem(new MenuItem("Turkey Sandwich", "Delicious turkey sandwich with cheese, lettuce on whole wheat",8, 10.00, Arrays.asList("Turkey", "Cheese", "Lettuce", "Whole Wheat")));
//        menu.addMenuItem(new MenuItem("Ham Sandwich", "Yummy ham sandwich served with lettuce, mayonnaise, cheese on whole wheat",3, 10.00, Arrays.asList("Ham","Lettuce", "Mayonnaise", "Cheese", "Whole Wheat")));

////        Adds ingredients to inventory
//        inventory.addInitialIngredient("Espresso ", 100, 20);
//        inventory.addInitialIngredient("Cherry syrup(1 )", 100, 20);
//        inventory.addInitialIngredient("Tonic water", 100, 20);

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

//          EVERYTHING BELOW THIS WORKS
//        menu.manageMenu();
//        menu.addNewMenuItem();
//        menu.removeMenuItem();
//        menu.editMenuItem();
//        menu.getMenuItems();

        //TABLE-MANAGEMENT works
//        table.editTable();

    }



}
