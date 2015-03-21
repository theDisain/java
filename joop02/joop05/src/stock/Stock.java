package stock;

import Items.Item;
import orders.Order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Ain-Joonas on 06.03.2015.
 */
public class Stock {
    private HashMap<Item, Integer> items = new HashMap<Item, Integer>();

    @Override
    public String toString() {
        return "Stock{" +
                "items=" + items +
                '}' + '\t';
    }

    public void receive(Order order){
        Set<Item> keys = order.getKeySet();
        Iterator<Item> iter = keys.iterator();
        while(iter.hasNext()){
            Item i = iter.next();
            Integer n = order.getQty(i);

            if(items.containsKey(i)){
                n += items.get(i);
            }
            items.put(i, new Integer(n));
        }
    }
    public int getAvailable(Item item){
        if(items.containsKey(item))
            return items.get(item); // return value - number of items
        return 0;
    }
}
