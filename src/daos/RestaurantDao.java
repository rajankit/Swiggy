package daos;

import models.Restaurant;
import java.util.*;

public class RestaurantDao {
    private static Map<String, Restaurant> restaurantMap = new HashMap<>();
    private static Map<String, Integer> restaurantUsage = new HashMap<>();

    private RestaurantDao() {}

    public static Map<String, Restaurant> getRestaurantMap() {
        return restaurantMap;
    }

    public static void setRestaurantMap(Map<String, Restaurant> restaurantMap) {
        RestaurantDao.restaurantMap = restaurantMap;
    }

    public static Map<String, Integer> getRestaurantUsage() {
        return restaurantUsage;
    }

    public static void setRestaurantUsage(Map<String, Integer> restaurantUsage) {
        RestaurantDao.restaurantUsage = restaurantUsage;
    }
}
