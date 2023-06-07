package restaurant.controllers;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import restaurant.models.InventoryItem;

public class InventoryManagement {

    static List<InventoryItem> listOfIngredients = new ArrayList<>();
    static InventoryManagement inventory = new InventoryManagement();

    //will add it to the listOfIn if the item is already made
    public void updateIngredientInventory() {
//      adding more inventory
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the item to update?");
        String itemName = scanner.nextLine();

        System.out.println("How many units of this item would you like to add?");
        int unitsToAdd = scanner.nextInt();
        scanner.nextLine();
        System.out.println(unitsToAdd);

        for(int i = 0; i < listOfIngredients.size(); i++){
            System.out.println(listOfIngredients.get(i).getItemName().equals(itemName));
            if(listOfIngredients.get(i).getItemName().equals(itemName)){
                    listOfIngredients.get(i).setQuantity(listOfIngredients.get(i).getQuantity() + unitsToAdd);
                System.out.println(listOfIngredients.get(i).getQuantity());
            }
        }
//WHY WON'T YOU MATH???
//        listOfIngredients.stream().filter(ingredient -> ingredient.getItemName().equals(itemName).forEach(ingredient -> {
//            System.out.println(ingredient.getQuantity() + unitsToAdd);

//        });

    }

    //will make the inventory item from the parameter
    public void addNewIngredient(String itemName, int quantity, int threshold) {
        for(int i = 0; i < listOfIngredients.size(); i++){
            if(listOfIngredients.get(i).getItemName().equals(itemName)){
                System.out.println("Ingredient already exists.");
            }else{
                InventoryItem item = new InventoryItem(itemName, quantity, threshold);
                listOfIngredients.add(item);
            }
        }

    }

    public void useIngredient(String ingredientName) {
        listOfIngredients.stream().filter(ingredient -> ingredient.getItemName().equals(ingredientName)).forEach(ingredient -> {
            ingredient.setQuantity(ingredient.getQuantity() - 1);
            if (ingredient.getQuantity() <= ingredient.getThreshold()) {
                System.out.println("Alert--- Only " + ingredient.getQuantity() + " left in inventory");
            }
        });
    }

    public void checkInventory() {
        for (int i = 0; i < listOfIngredients.size(); i++) {
            System.out.println(listOfIngredients.get(i).getItemName() + ": " + listOfIngredients.get(i).getQuantity());
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

    public static void manageInventory(InventoryManagement inventoryManagement) {
        System.out.println("What would you like to do with the inventory?");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter 1 to check inventory.");
            System.out.println("Enter 2 to add more inventory.");
            System.out.println("Enter 3 to add new item.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    inventory.checkInventory();
                    break;
                case 2:
                    inventory.updateIngredientInventory();
                    break;
                case 3:
                    System.out.println("What is the ingredient you would like to add?");
                    String itemName = scanner.nextLine();

                    System.out.println("How much of this ingredient is there?");
                    int quantity = Integer.valueOf(scanner.nextLine());

                    System.out.println("How much of this ingredient should be left at MINIMUM before we order more?");
                    int threshold = Integer.valueOf(scanner.nextLine());

                    inventory.addNewIngredient(itemName, quantity, threshold);
                    break;

                default:
                    System.out.println("Exiting inventory management. Goodbye.");
                    running = false;
                    break;

            }
        }

    }

    public static void main(String[] args) {

        inventory.addNewIngredient("8oz Coffee Abomination", 50, 5);
        inventory.addNewIngredient("8oz Oat Milk Latte", 50, 5);
        inventory.addNewIngredient("8oz Almond Milk Latte", 50, 5);
        inventory.addNewIngredient("8oz Skim Milk Latte", 50, 3);
        inventory.addNewIngredient("Oatmeal", 35, 4);
        inventory.addNewIngredient("Bacon BreakFast Sandwich", 30, 2);
        inventory.addNewIngredient("Turkey Sandwich", 30, 2);
        inventory.addNewIngredient("Ham Sandwich", 30, 2);

        manageInventory(inventory);
    }
}