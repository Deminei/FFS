import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import restaurant.models.InventoryItem;

public class TestInventoryItem {

    @Test
    public void testGetItemName() {
        // Arrange
        String expectedItemName = "Apple";
        InventoryItem item = new InventoryItem(expectedItemName, 10, 5);

        // Act
        String actualItemName = item.getItemName();

        // Assert
        Assertions.assertEquals(expectedItemName, actualItemName);
    }
    @Test
    public void testGetItemNameAfterMultipleSetItemName() {
        // Arrange
        String initialItemName = "Apple";
        String updatedItemName = "Banana";
        InventoryItem item = new InventoryItem(initialItemName, 10, 5);

        // Act
        item.setItemName("Orange");
        item.setItemName(updatedItemName);

        // Assert
        Assertions.assertEquals(updatedItemName, item.getItemName());
    }
    @Test
    public void testGetItemNameAfterNameChange() {
        // Arrange
        String initialItemName = "Orange";
        String updatedItemName = "Mango";
        InventoryItem item = new InventoryItem(initialItemName, 8, 4);

        // Act
        String actualInitialItemName = item.getItemName();
        item.setItemName(updatedItemName);
        String actualUpdatedItemName = item.getItemName();

        // Assert
        Assertions.assertEquals(initialItemName, actualInitialItemName);
        Assertions.assertEquals(updatedItemName, actualUpdatedItemName);
    }
    @Test
    public void testSetItemName() {
        // Arrange
        String initialItemName = "Apple";
        String updatedItemName = "Banana";
        InventoryItem item = new InventoryItem(initialItemName, 10, 5);

        // Act
        item.setItemName(updatedItemName);

        // Assert
        Assertions.assertEquals(updatedItemName, item.getItemName());
    }
}
