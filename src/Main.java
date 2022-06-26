import enums.Criteria;
import models.Item;
import service.OrderService;
import service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please order something");
        RestaurantService restaurantService = new RestaurantService();
        OrderService orderService = new OrderService();

        List<Item> menu = new ArrayList<>();

        // add res 1
        menu.add(new Item("i1", 100));
        menu.add(new Item("i2", 80));
        menu.add(new Item("i3", 120));
        restaurantService.addRestaurant("R1", 3, 4, menu);
        menu.clear();

        // add res 2
        menu.add(new Item("i4", 160));
        menu.add(new Item("i5", 280));
        restaurantService.addRestaurant("R2", 2, 4.5, menu);
        menu.clear();

        // add item to res 1
        restaurantService.addItem("R1", new Item("i10", 130));
        restaurantService.addItem("R2", new Item("i10", 520));

        // update item to res 1
        restaurantService.updateItem("R1", new Item("i10", 250));
        restaurantService.updateItem("R2", new Item("i5", 50));

        ArrayList<String> orderItems = new ArrayList<String>() {
            {
                add("1 i1");
                add("2 i3");
                add("1 i2");
            }
        };

        orderService.placeOrder("order_1", "Harish", orderItems, Criteria.COST);

        orderItems = new ArrayList<String>() {
            {
                add("1 i10");
                add("2 i5");
            }
        };
        orderService.placeOrder("order_2", "Alice", orderItems, Criteria.COST);

        // print item details
        // itemService.showItems();
        System.out.println();
        orderService.showOrder();
        System.out.println();
        restaurantService.showRestaurantsUsage();
        System.out.println();
        orderService.updateCompletedOrderStatus("order_1");
        orderService.updateCompletedOrderStatus("order_2");

        restaurantService.showRestaurantsUsage();
        orderService.placeOrder("order_2", "Alice", orderItems, Criteria.RATING);

        System.out.println();
        orderService.showOrder();
    }
}