package restaurant.controllers;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvException;
import restaurant.models.MenuItem;
import java.util.Arrays;


public class MenuManagement {
    private static final String MENU_FILE_PATH = "./target/menu.csv";

    public static List<String[]> getMenuData() {
        List<String[]> menuData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(MENU_FILE_PATH))) {
            menuData = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return menuData;
    }

//    private static File file = new File("./menutestcsv.csv");
//
//
//    public List<String[]> fileReader(){
//        try {
//            FileReader outputReader = new FileReader(file);
//            CSVReader reader = new CSVReader(outputReader);
//            menuData = reader.readAll();
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return menuData;
//    }
//    public void fileWriter() {
//        try {
//            FileWriter outputFile = new FileWriter(file);
//            CSVWriter writer = new CSVWriter(outputFile, '|', CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
//            writer.writeAll(menuData);
//            writer.close();
//            } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void add(MenuItem item){
        menuData.add((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()), Double.toString(item.getPrice()), item.getIngredients().toString()}));
        fileWriter();
    }
   public void delete(MenuItem item){
       menuData.remove((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()),String.valueOf(item.getPrice()), item.getIngredients().toString()}));
    fileWriter();
   }
   public void edit(MenuItem item){
    boolean running = true;
    while(running){
        int option = prompt("Pick from the list of options below", new String[]{
            "update the item's name",
            "update the item's description",
            "update the item's preparation time",
            "update the item's price",
            "Update the item's ingredients"
    });
 
    switch(option){
        case 1: 
        item.setName(prompt("What's the new name?"));
        break;
        case 2: item.setDescription(prompt("What would you like the new description to say?"));
        break;
        case 3: item.setPreparationTime(Integer.parseInt((prompt("What is the new preparation time?"))));
        break;
        case 4: item.setPrice(Double.parseDouble((prompt("What is the new price? EX:0.00"))));
        break;
        case 5: item.setIngredients(Arrays.asList(prompt("What are the new ingredients?").split("|")));
        break;
        default:
        System.out.println("Logging out. Goodbye!");
        running = false;
        break;
    }
    }
   }
public int prompt(String question, String[] options){
    System.out.println(question);
    for(int i = 0; i < options.length; i++){
        System.out.println(i + ": " + options[i]);
    }
    String input = prompt("");
    int parsedInput = Integer.parseInt(input);
    return parsedInput;
}
public String prompt(String question){
    System.out.println(question);
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    return input;
}

            // menuData.add(new MenuItem("8oz Coffee Abomination", "Delicious tonic water, coffee beverage with a shot of espresso",3, 8.00, Arrays.asList("Tonic water", "Cherry syrup", "Espresso")));
            // menuData.add(new MenuItem("8oz Oat Milk Latte", "Shots of espresso served over ice with oat milk", 2, 6.00, Arrays.asList("Espresso", "Oat milk", "Ice")));
            // menuData.add(new MenuItem("8oz Almond Milk Latte", "Shots of espresso served over ice with almond milk",2, 6.00, Arrays.asList("Espresso", "Almond milk", "Ice")));
            // menuData.add(new MenuItem("Oatmeal", "Heathly Oatmeal topped with blueberries, honey, and brown sugar",5, 5.00, Arrays.asList("Oatmeal Package", "Blueberry Package", "Honey Package", "Brown Sugar Package")));
            // menuData.add(new MenuItem("Bacon BreakFast Sandwich", "Amazing bacon breakFast sandwich with egg, cheese on english muffin",8, 8.00, Arrays.asList("Bacon", "Egg", "Cheese", "English Muffin")));
            // menuData.add(new MenuItem("Turkey Sandwich", "Delicious turkey sandwich with cheese, lettuce on whole wheat",8, 10.00, Arrays.asList("Turkey", "Cheese", "Lettuce", "Whole Wheat")));
            // menuData.add(new MenuItem("Ham Sandwich", "Yummy ham sandwich served with lettuce, mayonnaise, cheese on whole wheat",3, 10.00, Arrays.asList("Ham","Lettuce", "Mayonnaise", "Cheese", "Whole Wheat")));

}

