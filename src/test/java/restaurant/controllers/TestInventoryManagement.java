package restaurant.controllers;
import org.junit.jupiter.api.*;
import restaurant.models.InventoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestInventoryManagement {
    @Test
    void testUpdateIngredientInventory(){

        List<InventoryItem> listOfIngredients = new ArrayList<>();

        InventoryItem newItem = new InventoryItem("Eggs", 12,3);
        listOfIngredients.add(newItem);

        String itemName = "Eggs";
        int unitsToAdd = 12;


        for (InventoryItem listOfIngredient : listOfIngredients) {
            if (listOfIngredient.getItemName().equals(itemName)) {
                listOfIngredient.setQuantity(listOfIngredient.getQuantity() + unitsToAdd);
            }
        }

        Assertions.assertEquals("Eggs", listOfIngredients.contains("Eggs"));
      //  Assertions.assertEquals(24,listOfIngredients.get(0).setQuantity(listOfIngredients.get(0).getQuantity() + unitsToAdd));

    }

}

