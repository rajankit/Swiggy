package strategy;

import daos.ItemDao;
import service.RestaurantService;
import utils.Pair;

import java.util.ArrayList;
import java.util.Map;

public class OrderByLowCost  extends OrderStrategy {
    private static Map<String, ArrayList<Pair>> itemList = ItemDao.getItemList();
    private RestaurantService restaurantService = new RestaurantService();
    public OrderByLowCost(int quantity, String itemName) {
        super(quantity, itemName);
    }

    @Override
    public String orderFromRestaurant() {
        ArrayList<Pair> resList = itemList.get(this.itemName);

        int maxCost = 100000;
        String rid = "";

        for(Pair pi : resList) {
            if ((int)pi.getFirst() < maxCost && restaurantService.canOrder((String)pi.getSecond())) {
                maxCost = (int)pi.getFirst();
                rid = (String) pi.getSecond();
            }
        }

        return rid;
    }
}
