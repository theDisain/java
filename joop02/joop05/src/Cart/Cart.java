package Cart;

import Items.Item;
import orders.Order;

import java.util.HashMap;

/**
 * Created by Ain-Joonas on 12.03.2015.
 */
public class Cart {
    private HashMap<Item, Integer> items = new HashMap<Item, Integer>();
    public double sumPrice;
    public Cart() {}
    public boolean add(Item item, int q){
        if(q<=0){
            return false;
        }
        if(items.containsKey(item)){
            Integer n = items.get(item);
            q += n;
        }
        items.put(item, new Integer(q));
        sumPrice += item.getPrice() * q;
        return true;
    }
    public Integer getQty(Item item){
        return items.get(item);
    }
    public void change(Item item, int q){
        if(items.get(item) != q){
            q = items.get(item);
        }
    }
    public String toString(Item item) {
        return item.getName();
    }
    public void remove(Item item){
        sumPrice -= item.getPrice() * items.get(item).doubleValue();
        items.remove(item);
        System.out.println("Item " + toString(item) + " deleted!");
    }
    public double getTotal(){
        return sumPrice;
    }
    public Order checkout(){
        Order order = new Order();
        order.rows = this.items;
        return order;
    }
}
