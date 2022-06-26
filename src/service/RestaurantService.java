package service;
import daos.RestaurantDao;
import models.Item;
import models.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    ItemDetailsService itemService = new ItemDetailsService();
    private Map<String, Restaurant> restaurantMap = RestaurantDao.getRestaurantMap();
    private Map<String, Integer> restaurantUsage = RestaurantDao.getRestaurantUsage();
    public void addRestaurant(String id, int limit, double rating, List<Item> menu) {

        Map<String, Integer> itemsMap = new HashMap<>();

        for(Item item : menu) {
            itemsMap.put(item.getItemName(), item.getPrice());
        }

        Restaurant restaurant = new Restaurant(id, limit, rating, itemsMap);
        restaurantMap.put(id, restaurant);

        // keep track of items for ordering
        itemService.addItemDetails(menu, restaurant);
    }

    public void addItem(String id, Item item) {
        if (restaurantMap.containsKey(id)) {
            restaurantMap.get(id).addItem(item);

            // keep track of items for ordering
            itemService.addItemDetails(item, restaurantMap.get(id));
        }
    }

    public void updateItem(String id, Item item) {
        if (restaurantMap.containsKey(id) && restaurantMap.get(id).getMenu().containsKey(item.getItemName())) {
            restaurantMap.get(id).getMenu().put(item.getItemName(), item.getPrice());

            // keep track of items for ordering
            itemService.addItemDetails(item, restaurantMap.get(id));
        }
    }

    public void updateRestaurantUsage(String id, int usage) {
        restaurantUsage.put(id, (restaurantUsage.containsKey(id) ? restaurantUsage.get(id) : 0) + usage);
    }

    public boolean canOrder(String id) {
        if (restaurantUsage.containsKey(id) == false) {
            restaurantUsage.put(id, 0);
        }

        if (restaurantUsage.get(id) == restaurantMap.get(id).getMaxOrderLimit()) {
            return false;
        }

        return true;
    }

    public boolean isRatingHigh(double curr, String id) {
        if (curr < restaurantMap.get(id).getRating()) return true;
        return false;
    }

    public double getRating(String id) {
        return restaurantMap.get(id).getRating();
    }

    public void showRestaurants() {
        for(String res : restaurantMap.keySet()) {
            Restaurant restaurant = restaurantMap.get(res);
            System.out.println(restaurant.getId() + " " + restaurant.getMaxOrderLimit() + " " + restaurant.getRating());

            for(String item : restaurant.getMenu().keySet()) {
                System.out.println(item + " " + restaurant.getMenu().get(item));
            }
        }
    }

    public void showRestaurantsUsage() {
        for(String res : restaurantUsage.keySet()) {
            System.out.println(res + " " + restaurantUsage.get(res));
        }
    }

}
