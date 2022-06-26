package models;

import enums.OrderStatus;
import java.util.*;
import utils.Pair;


public class Order {
    private List<Pair> restaurant;
    private String id;
    private OrderStatus status;
    private String user;

    public Order(String id, String user, List<Pair> restaurant) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.status = OrderStatus.ACCEPTED;
    }

    public void setRestaurant(List<Pair> restaurant) {
        this.restaurant = restaurant;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public String getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public List<Pair> getRestaurant() {
        return restaurant;
    }
}
