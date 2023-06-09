package restaurant.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import restaurant.models.InventoryItem;

public class InventoryManagement {

    List<InventoryItem> listOfIngredients = new ArrayList<>();


    //will add it to the listOfIn if the item is already made
    public void updateIngredientInventory() {
//      adding more inventory
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the item to update?");
        String itemName = scanner.nextLine();

        System.out.println("How many units of this item would you like to add?");
        int unitsToAdd = scanner.nextInt();
        scanner.nextLine();

        for (InventoryItem listOfIngredient : listOfIngredients) {
            if (listOfIngredient.getItemName().equals(itemName)) {
                listOfIngredient.setQuantity(listOfIngredient.getQuantity() + unitsToAdd);
            }
        }

    }

    public void addInitialIngredient(String itemName, int quantity, int threshold){
        InventoryItem item = new InventoryItem(itemName, quantity, threshold);
        listOfIngredients.add(item);
    }
    //will make the inventory item from the parameter
    public void addNewIngredient(String itemName, int quantity, int threshold) {
        boolean ingredientExists = false;

        for (InventoryItem item : listOfIngredients) {
            if (item.getItemName().equals(itemName)) {
                ingredientExists = true;
                break;
            }
        }

        if (ingredientExists) {
            System.out.println("Ingredient already exists.");
        } else {
            InventoryItem item = new InventoryItem(itemName, quantity, threshold);
            listOfIngredients.add(item);
        }
    }

    public void useIngredient(String itemName) {

        for (InventoryItem ingredient : listOfIngredients) {
            if (ingredient.getItemName().equals(itemName)) {
                ingredient.setQuantity(ingredient.getQuantity() - 1);
                System.out.println(ingredient.getQuantity());
                if (ingredient.getQuantity() <= ingredient.getThreshold()) {
                    System.out.println("Alert--- Only " + ingredient.getQuantity() + " left in inventory");
                }
                break; // Exit the loop after finding and updating the ingredient
            }
        }
    }


    public void checkInventory() {
        for (InventoryItem listOfIngredient : listOfIngredients) {
            System.out.println(listOfIngredient.getItemName() + ": " + listOfIngredient.getQuantity());
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

    public void manageInventory() {
        System.out.println("What would you like to do with the inventory?");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter 1 to check inventory.");
            System.out.println("Enter 2 to add more inventory.");
            System.out.println("Enter 3 to add new item.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.parseInt(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    checkInventory();
                    break;
                case 2:
                    updateIngredientInventory();
                    break;
                case 3:
                    System.out.println("What is the ingredient you would like to add?");
                    String itemName = scanner.nextLine();

                    System.out.println("How much of this ingredient is there?");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    System.out.println("How much of this ingredient should be left at MINIMUM before we order more?");
                    int threshold = Integer.parseInt(scanner.nextLine());

                    addNewIngredient(itemName, quantity, threshold);
                    break;

                default:
                    System.out.println("Exiting inventory management. Goodbye.");
                    running = false;
                    break;

            }
        }

    }

//    public static void main(String[] args) {

//        addInitialIngredient("8oz Coffee Abomination", 50, 5);
//        addInitialIngredient("8oz Oat Milk Latte", 50, 5);
//        addInitialIngredient("8oz Almond Milk Latte", 50, 5);
//        addInitialIngredient("8oz Skim Milk Latte", 50, 3);
//        addInitialIngredient("Oatmeal", 35, 4);
//        addInitialIngredient("Bacon BreakFast Sandwich", 30, 2);
//        addInitialIngredient("Turkey Sandwich", 30, 2);
//        addInitialIngredient("Ham Sandwich", 30, 2);
//        System.out.println(listOfIngredients);
//        addNewIngredient("8oz Almond Milk Latte", 50, 5);
//        System.out.println(listOfIngredients);
//
//    }
}