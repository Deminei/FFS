package restaurant.models;

public class InventoryItem {
    private String itemName;
    private int quantity;
    private int threshold;

    public InventoryItem(String itemName, int quantity, int threshold) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
