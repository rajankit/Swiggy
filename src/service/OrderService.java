package service;

import daos.OrderDao;
import enums.Criteria;
import enums.OrderStatus;
import models.Order;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class OrderService {
    private RestaurantService restaurantService = new RestaurantService();
    private Map<String, Order> orderMap = OrderDao.getOrderMap();
    private PlaceOrder placeOrder = new PlaceOrder();

    public void placeOrder(String id, String user, ArrayList<String> orderItems, Criteria criteria) {
        ArrayList<Pair> res = new ArrayList<>();
        for(String order : orderItems) {
            String[] orderDetails = order.split(" ");

            String itemName = orderDetails[1];
            int qty =  parseInt(orderDetails[0]);
            String assignedRestaurant = placeOrder.assignOrder(itemName, qty, criteria);

            res.add(new Pair<>(itemName, assignedRestaurant));
            restaurantService.updateRestaurantUsage(assignedRestaurant, 1);
        }

        Order order = new Order(id, user, res);
        orderMap.put(id, order);
    }

    public void updateCompletedOrderStatus(String id) {
        List<Pair> restaurants = orderMap.get(id).getRestaurant();

        for(Pair pi : restaurants) {
            restaurantService.updateRestaurantUsage((String)pi.getSecond(), -1);
        }

        orderMap.get(id).setStatus(OrderStatus.COMPLETED);
    }

    public void showOrder() {
        for(String id : orderMap.keySet()) {
            if (orderMap.get(id).getStatus() == OrderStatus.COMPLETED) {
                continue;
            }

            System.out.println(id);

            for(Pair pi : orderMap.get(id).getRestaurant()) {
                System.out.println(pi.getFirst() + " " + pi.getSecond());
            }
        }
    }
}
