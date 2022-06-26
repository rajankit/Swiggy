package strategy;

public abstract class OrderStrategy {
    int quantity;
    String itemName;

    public OrderStrategy(int quantity, String itemName) {
        this.quantity = quantity;
        this.itemName = itemName;
    }

    public abstract String orderFromRestaurant();
}
