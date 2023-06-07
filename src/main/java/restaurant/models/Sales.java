package restaurant.models;


import java.util.List;

public class Sales {
    private double totalRevenue;
    private List<String> popularItems;
    private List<Integer> tablesWithMostOrders;

    public Sales(double totalRevenue, List<String> popularItems, List<Integer> tablesWithMostOrders) {
        this.totalRevenue = totalRevenue;
        this.popularItems = popularItems;
        this.tablesWithMostOrders = tablesWithMostOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<String> getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(List<String> popularItems) {
        this.popularItems = popularItems;
    }

    public List<Integer> getTablesWithMostOrders() {
        return tablesWithMostOrders;
    }

    public void setTablesWithMostOrders(List<Integer> tablesWithMostOrders) {
        this.tablesWithMostOrders = tablesWithMostOrders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Revenue: $").append(totalRevenue).append("\n");
        sb.append("Popular Items: ").append(popularItems).append("\n");
        sb.append("Tables with Most Orders: ").append(tablesWithMostOrders).append("\n");
        return sb.toString();
    }
}


