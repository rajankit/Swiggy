package service;

import enums.Criteria;
import strategy.OrderByLowCost;
import strategy.OrderByRating;
import strategy.OrderStrategy;

public class PlaceOrder {
    public String assignOrder(String itemName, int qty, Criteria criteria) {
        OrderStrategy orderStrategy = null;

        if (criteria == Criteria.RATING) {
            orderStrategy = new OrderByRating(qty, itemName);
        } else if (criteria == Criteria.COST) {
            orderStrategy = new OrderByLowCost(qty, itemName);
        }

        return orderStrategy.orderFromRestaurant();
    }
}
