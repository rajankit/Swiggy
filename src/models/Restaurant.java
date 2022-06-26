package models;

import javafx.util.Pair;

import java.util.*;

public class Restaurant {
    private String id;
    private int maxOrderLimit;
    private Map<String, Integer> menu;
    private double rating;

    public Restaurant(String id, int maxOrderLimit, double rating, Map<String, Integer> menu) {
        this.id = id;
        this.maxOrderLimit = maxOrderLimit;
        this.rating = rating;
        this.menu = menu;
    }

    public String getId() {
        return this.id;
    }

    public double getRating() {
        return rating;
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public int getMaxOrderLimit() {
        return this.maxOrderLimit;
    }

    public void addItem(Item item) {
        menu.put(item.getItemName(), item.getPrice());
    }
}
