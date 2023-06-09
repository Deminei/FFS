package restaurant.controllers;

import org.junit.jupiter.api.*;
import restaurant.models.MenuItem;
import java.util.Collections;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MenuManagementTest {
    private MenuManagement menuManagement;
    private InputStream mockInputStream;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream mockOutputStream;

    @BeforeEach
    void setUp() {
        menuManagement = new MenuManagement();

        // Save the original System.out and redirect it to a mock OutputStream
        originalSystemOut = System.out;
        mockOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOutputStream));

        // Reset the menu items before each test
        menuManagement.getMenuItems().clear();
    }

    @Test
    void testAddMenuItem() {
        // Arrange
        MenuItem item = new MenuItem("Burger", "Delicious burger", 10, 8.99, Collections.singletonList("Beef, Lettuce, Tomato"));

        // Act
        menuManagement.addMenuItem(item);

        // Assert
        List<MenuItem> menuItems = menuManagement.getMenuItems();
        assertEquals(1, menuItems.size());
        assertEquals("Burger", menuItems.get(0).getName());
    }

    @Test
    void testAddNewMenuItem() {
        // Arrange
        mockInputStream = new ByteArrayInputStream("Pizza\nTasty pizza\n15\n12.99\nCheese, Tomato Sauce".getBytes());

        // Act
        System.setIn(mockInputStream);
        menuManagement.addNewMenuItem();

        // Assert
        List<MenuItem> menuItems = menuManagement.getMenuItems();
        assertEquals(1, menuItems.size());
        assertEquals("Pizza", menuItems.get(0).getName());
    }

    @Test
    void testRemoveMenuItem() {
        // Arrange
        menuManagement.addMenuItem(new MenuItem("Burger", "Delicious burger", 10, 8.99, Collections.singletonList("Beef, Lettuce, Tomato")));
        mockInputStream = new ByteArrayInputStream("Burger\n".getBytes());

        // Act
        System.setIn(mockInputStream);
        menuManagement.removeMenuItem();

        // Assert
        List<MenuItem> menuItems = menuManagement.getMenuItems();
        assertEquals(0, menuItems.size());
    }

    @Test
    void testEditMenuItem() {

        MenuItem pizza = new MenuItem("Pizza", "Tasty pizza", 15, 12.99, Collections.singletonList("Cheese, Tomato Sauce"));
        menuManagement.addMenuItem(pizza);
        mockInputStream = new ByteArrayInputStream("Pizza\n1\nNew Pizza Name\n0\n".getBytes());

        System.setIn(mockInputStream);
        menuManagement.editMenuItem();

        List<MenuItem> updatedMenuItems = menuManagement.getMenuItems();
        assertEquals("New Pizza Name", updatedMenuItems.get(0).getName());
    }

    @Test
    void testDisplayMenuItems() {
        MenuItem burger = new MenuItem("Burger", "Delicious burger", 10, 8.99, Collections.singletonList("Beef, Lettuce, Tomato"));
        MenuItem pizza = new MenuItem("Pizza", "Tasty pizza", 15, 12.99, Collections.singletonList("Cheese, Tomato Sauce"));
        menuManagement.addMenuItem(burger);
        menuManagement.addMenuItem(pizza);

        menuManagement.displayMenuItems();

        String consoleOutput = mockOutputStream.toString().trim();
        String expectedOutput = "1. Burger - Delicious burger - $8.99\n2. Pizza - Tasty pizza - $12.99";
        assertEquals(expectedOutput, consoleOutput);
    }

    @Test
    void testManageMenu_ExitOption() {
        mockInputStream = new ByteArrayInputStream("0\n".getBytes());

        System.setIn(mockInputStream);
        menuManagement.manageMenu();

        String consoleOutput = mockOutputStream.toString().trim();
        String expectedOutput = "What would you like to do with the menu?\n" +
                "Enter 1 to view the menu.\n" +
                "Enter 2 to add an item.\n" +
                "Press 3 to remove an item.\n" +
                "Press 4 to edit an item.\n" +
                "Enter 0 to exit.\n" +
                "Leaving menu management. Goodbye.";
        assertEquals(expectedOutput, consoleOutput);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }
}



