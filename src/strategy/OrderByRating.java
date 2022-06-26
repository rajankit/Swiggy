package strategy;

import daos.ItemDao;
import service.RestaurantService;
import utils.Pair;

import java.util.ArrayList;
import java.util.Map;

public class OrderByRating extends OrderStrategy {
    private static Map<String, ArrayList<Pair>> itemList = ItemDao.getItemList();
    private RestaurantService restaurantService = new RestaurantService();
    public OrderByRating(int quantity, String itemName) {
        super(quantity, itemName);
    }

    @Override
    public String orderFromRestaurant() {
        ArrayList<Pair> resList = itemList.get(this.itemName);

        double rating = 0;
        String rid = "";

        for(Pair pi : resList) {
            String resId =  (String)pi.getSecond();
            if (restaurantService.canOrder(resId) && restaurantService.isRatingHigh(rating, resId)) {
                rating = restaurantService.getRating(resId);
                rid = resId;
            }
        }

        return rid;
    }
}
