package daos;

import utils.Pair;

import java.util.*;

public class ItemDao {
    private static Map<String, ArrayList<Pair>> itemList = new HashMap<>();

    private ItemDao() {}

    public static Map<String, ArrayList<Pair>> getItemList() {
        return itemList;
    }
}
