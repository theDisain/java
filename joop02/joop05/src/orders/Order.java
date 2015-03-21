package orders;

import Items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ain-Joonas on 06.03.2015.
 */
public class Order {
    protected int serialNumber;
    protected static int counter = 0;
    public HashMap<Item, Integer> rows = new HashMap<Item, Integer>();

    @Override
    public String toString() {

        String result = "Order{" +
                "serial #" + serialNumber +
                '}';
        for( Map.Entry<Item, Integer>entry: rows.entrySet()){
            Item key = entry.getKey();
            Integer value = entry.getValue();
            result += "\n\t" + key + " " + value + "tk";
        }
        return result;
    }

    public Order(){
        serialNumber = counter++;

    }
    public Set<Item> getKeySet(){
        return rows.keySet();
    }
    public Integer getQty(Item item){
        return rows.get(item);
    }
    public boolean add(Item item, int q){
        if(q<=0){
            return false;
        }
        if(rows.containsKey(item)){
           Integer n = rows.get(item);
            q += n;
        }
        rows.put(item, new Integer(q));
        return true;
    }
    public void dispatch(){

    }

}
