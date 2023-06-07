package restaurant.models;

import java.util.Collections;
import java.util.List;

public class MenuItem {
    private String name;
    private String description;
    private int preparationTime;
    private double price;
    private List<String> ingredients;

    public MenuItem(String name, String description, int preparationTime, double price, String ingredients) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = Collections.singletonList(ingredients);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Preparation Time: ").append(preparationTime).append(" minutes").append("\n");
        sb.append("Price: $").append(price).append("\n");
        sb.append("Ingredients: ").append(ingredients).append("\n");
        return sb.toString();
    }
}
