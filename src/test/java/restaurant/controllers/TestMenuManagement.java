package restaurant.controllers;
//Chosen feature to test:  2. Menu Management: Implement a way to add, remove, and edit menu items. Each item should have the properties described below.
//Implement a system to add, delete, and edit items from the restaurant menu.
//Each menu item should include properties such as:
//Item Name
//Item Description
//Preparation Time
//Item Price
//Ingredients list
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.MenuItem;

import java.util.List;

public class TestMenuManagement {

    private MenuManagement menuManagement;

    @BeforeEach
    public void setUp() {
        menuManagement = new MenuManagement();
    }


    @Test
    public void testRemoveMenuItem() {
        MenuItem item1 = new MenuItem("Coffee", "Delicious coffee", 3, 5.0, List.of("Coffee beans", "Water"));
        MenuItem item2 = new MenuItem("Tea", "Refreshing tea", 2, 3.5, List.of("Tea leaves", "Hot water"));

        menuManagement.addMenuItem(item1);
        menuManagement.addMenuItem(item2);

        menuManagement.removeMenuItem();

        List<MenuItem> menuItems = menuManagement.getMenuItems();
        Assertions.assertEquals(1, menuItems.size());
        MenuItem remainingItem = menuItems.get(0);
        Assertions.assertEquals(item2.getName(), remainingItem.getName());
    }

    @Test
    public void testEditMenuItem() {
        MenuItem item = new MenuItem("Coffee", "Delicious coffee", 3, 5.0, List.of("Coffee beans", "Water"));

        menuManagement.addMenuItem(item);

        MenuItem newItem = new MenuItem("Iced Coffee", "Chilled coffee", 5, 6.5, List.of("Coffee beans", "Ice", "Milk"));

        menuManagement.editMenuItem();

        List<MenuItem> menuItems = menuManagement.getMenuItems();
        Assertions.assertEquals(1, menuItems.size());
        MenuItem editedItem = menuItems.get(0);
        Assertions.assertEquals(newItem.getName(), editedItem.getName());
        Assertions.assertEquals(newItem.getDescription(), editedItem.getDescription());
        Assertions.assertEquals(newItem.getPreparationTime(), editedItem.getPreparationTime());
        Assertions.assertEquals(newItem.getPrice(), editedItem.getPrice());
        Assertions.assertEquals(newItem.getIngredients(), editedItem.getIngredients());
    }
}
