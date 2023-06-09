package restaurant;

import restaurant.controllers.*;
import restaurant.models.*;
import java.util.Arrays;

import static restaurant.models.Table.Status.*;

public class App {

    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
//      Instantiates Control classes
        TableManagement tableManagement = new TableManagement();
        InventoryManagement inventory = new InventoryManagement();
        MenuManagement menu = new MenuManagement();
        OrderProcessing orderProcessing = new OrderProcessing();
        UserLogin userLogin = new UserLogin();
        SalesReport report = new SalesReport();
//        Populate list of tables
        tableManagement.addTableToRestaurant(1,4, OCCUPIED);
        tableManagement.addTableToRestaurant(2,6, AVAILABLE);
        tableManagement.addTableToRestaurant(3,2, AVAILABLE);
        tableManagement.addTableToRestaurant(4,4, AVAILABLE);

//        Populates Menu
//        menu.addMenuItem(new MenuItem("8oz Coffee Abomination", "Delicious tonic water, coffee beverage with a shot of espresso",3, 8.00, Arrays.asList("Tonic water", "Cherry syrup", "Espresso")));
//        menu.addMenuItem(new MenuItem("8oz Oat Milk Latte", "Shots of espresso served over ice with oat milk", 2, 6.00, Arrays.asList("Espresso", "Oat milk", "Ice")));
//        menu.addMenuItem(new MenuItem("8oz Almond Milk Latte", "Shots of espresso served over ice with almond milk",2, 6.00, Arrays.asList("Espresso", "Almond milk", "Ice")));
//        menu.addMenuItem(new MenuItem("Oatmeal", "Healthy Oatmeal topped with blueberries, honey, and brown sugar",5, 5.00, Arrays.asList("Oatmeal Package", "Blueberry Package", "Honey Package", "Brown Sugar Package")));
//        menu.addMenuItem(new MenuItem("Bacon BreakFast Sandwich", "Amazing bacon breakFast sandwich with egg, cheese on english muffin",8, 8.00, Arrays.asList("Bacon", "Egg", "Cheese", "English Muffin")));
//        menu.addMenuItem(new MenuItem("Turkey Sandwich", "Delicious turkey sandwich with cheese, lettuce on whole wheat",8, 10.00, Arrays.asList("Turkey", "Cheese", "Lettuce", "Whole Wheat")));
//        menu.addMenuItem(new MenuItem("Ham Sandwich", "Yummy ham sandwich served with lettuce, mayonnaise, cheese on whole wheat",3, 10.00, Arrays.asList("Ham","Lettuce", "Mayonnaise", "Cheese", "Whole Wheat")));

//        Adds ingredients to inventory
        inventory.addNewIngredient("Espresso ", 100, 20);
        inventory.addNewIngredient("Cherry syrup ", 50, 20);
        inventory.addNewIngredient("Tonic water", 50, 20);
        inventory.addNewIngredient("8oz Oat Milk", 50, 5);
        inventory.addNewIngredient("8oz Almond Milk", 50, 5);
        inventory.addNewIngredient("8oz Skim Milk", 50, 3);
        inventory.addNewIngredient("Oatmeal Package", 55, 8);
        inventory.addNewIngredient("Bacon", 50, 25);
        inventory.addNewIngredient("Turkey", 30, 10);
        inventory.addNewIngredient("Ham", 30, 10);
        inventory.addNewIngredient("Blueberry Package", 30, 4);
        inventory.addNewIngredient("Honey Package", 30, 4);
        inventory.addNewIngredient("Brown Sugar Package", 30, 4);
        inventory.addNewIngredient("Egg", 144, 12);
        inventory.addNewIngredient("Cheese", 60, 10);
        inventory.addNewIngredient("English Muffin", 30, 5);
        inventory.addNewIngredient("Whole Wheat", 50, 10);
        inventory.addNewIngredient("Lettuce", 30, 5);
        inventory.addNewIngredient("Mayonnaise", 30, 2);
        userLogin.findUser(tableManagement, menu, inventory, orderProcessing, report);

        report.generateSalesReport(orderProcessing, menu);
    }
}
