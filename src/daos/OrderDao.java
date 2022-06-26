package daos;

import models.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderDao {
    public  static Map<String, Order> orderMap = new HashMap<>();

    private OrderDao() {}
    public static Map<String, Order> getOrderMap() {
        return orderMap;
    }
}
