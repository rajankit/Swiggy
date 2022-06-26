package service;

import daos.ItemDao;
import models.Item;
import models.Restaurant;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDetailsService {

    private static Map<String, ArrayList<Pair>> itemList = ItemDao.getItemList();

    public void addItemDetails(List<Item> menu, Restaurant r) {
        for (Item item : menu) {
            addItemDetails(item, r);
        }
    }

    public void addItemDetails(Item item, Restaurant r) {
        if (!itemList.containsKey(item.getItemName())) {
            itemList.put(item.getItemName(), new ArrayList<>());
        }

        Pair pi = new Pair(item.getPrice(), r.getId());

        for(int i = 0; i < itemList.get(item.getItemName()).size(); i++) {
            Pair pii = itemList.get(item.getItemName()).get(i);

            if (pii.getSecond() == r.getId()) {
                itemList.get(item.getItemName()).get(i).setFirst(item.getPrice());
                return;
            }
        }

        itemList.get(item.getItemName()).add(pi);
    }

    public void showItems() {
        for(String id : itemList.keySet()) {
            System.out.print(id + " ");

            for(Pair pi : itemList.get(id)) {
                System.out.print(pi.getFirst() + " " + pi.getSecond() + " ");
            }
            System.out.println();
        }
    }
}

