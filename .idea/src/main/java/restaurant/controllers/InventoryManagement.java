package restaurant.controllers;
import java.util.ArrayList;
import java.util.List;

import restaurant.models.InventoryItem;

public class InventoryManagement {

    List<InventoryItem> listOfIngredients = new ArrayList<>();

    //will add it to the listOfIn if the item is already made
    public void addIngredient(InventoryItem item){
        listOfIngredients.add(item);
    }
    //will make the inventory item from the peremater
    public void addIngredient(String itemName, int quantity, int threshold){
        InventoryItem item = new InventoryItem(itemName, quantity, threshold);
        listOfIngredients.add(item);
    }
    public void useIngredient(String ingredientName){
        listOfIngredients.stream().filter(ingredient -> ingredient.getItemName() == ingredientName).forEach(ingredient -> {
            ingredient.setQuantity(ingredient.getQuantity() - 1); 
            if (ingredient.getQuantity() <= ingredient.getThreshold()){
                System.out.println("Alert--- Only " + ingredient.getQuantity() +" left in inventory");
            }
        });
    }
    public void checkInventory(){
        for (int i = 0; i < listOfIngredients.size(); i++) {
            System.out.println(listOfIngredients.get(i).getItemName() + ": " + listOfIngredients.get(i).getQuantity() );
        }
    }
    public void getIngredients(String ingredientName) {
        boolean ingredientFound = false;
        for (InventoryItem ingredient : listOfIngredients) {
            if (ingredient.getItemName().equals(ingredientName)) {
                System.out.println(ingredient.getItemName() + ": " + ingredient.getQuantity());
                ingredientFound = true;
                break;
            }
        }
        if (!ingredientFound) {
            System.out.println("Ingredient '" + ingredientName + "' not found.");
        }
    }


    //    addIngredient("8oz Coffee Abomination", 50, 5);
    //    addIngredient("8oz Oat Milk Latte", 50,5);
    //    addIngredient("8oz Almond Milk Latte", 50,5);
    //    addIngredient("8oz Skim Milk Latte", 50,3);
    //    addIngredient("Oatmeal", 35,4);
    //    addIngredient("Bacon BreakFast Sandwich", 30,2);
    //    addIngredient("Turkey Sandwich", 30,2);
    //    addIngredient("Ham Sandwich", 30,2);
   
}
