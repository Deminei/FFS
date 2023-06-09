package restaurant.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.InventoryItem;

import restaurant.controllers.InventoryManagement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList; // Import ArrayList class from java.util
class InventoryTest {

    private InventoryManagement inventoryManagement;
    private List<InventoryItem> listOfIngredients;

    @BeforeEach
    void setUp() {
        inventoryManagement = new InventoryManagement();
        listOfIngredients = new ArrayList<>();
        inventoryManagement.listOfIngredients = listOfIngredients;
    }

    @org.junit.jupiter.api.Test
    void testAddIngredient() {
        // Arrange
        InventoryItem item = new InventoryItem("Coffee", 10, 5);

        // Act
        inventoryManagement.addIngredient(item);

        // Assert
        Assertions.assertTrue(listOfIngredients.contains(item));
    }

    @org.junit.jupiter.api.Test
    void testAddIngredientWithExistingItem() {
        // Arrange
        InventoryItem existingItem = new InventoryItem("Coffee", 10, 5);
        listOfIngredients.add(existingItem);
        InventoryItem newItem = new InventoryItem("Coffee", 5, 5);

        // Act
        inventoryManagement.addIngredient(newItem);

        // Assert
        Assertions.assertTrue(listOfIngredients.contains(existingItem));
        Assertions.assertTrue(listOfIngredients.contains(newItem));
    }
    @org.junit.jupiter.api.Test
    void testAddIngredientWithDifferentItem() {
        // Arrange
        InventoryManagement inventoryManagement = new InventoryManagement();
        InventoryItem existingItem = new InventoryItem("Coffee", 10, 5);
        inventoryManagement.listOfIngredients.add(existingItem);
        InventoryItem newItem = new InventoryItem("Milk", 5, 5);

        // Act
        inventoryManagement.addIngredient(newItem);

        // Assert
        Assertions.assertTrue(inventoryManagement.listOfIngredients.contains(existingItem));
        Assertions.assertTrue(inventoryManagement.listOfIngredients.contains(newItem));
    }
}
