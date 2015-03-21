package Items;

import Cart.Cart;
import orders.Order;
import stock.Stock;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ain-Joonas on 06.03.2015.
 */
public class StockTest {
    public static void main(String[] args){
        Item i1 = new Item("Leib", 0.35);
        Item i2 = new Item("Leib", 0.35);
        System.out.println("i1 = " + i1.equals(i2));
        Set<Item> itemSet = new HashSet<Item>();
        System.out.println("Adding i1" + itemSet.add(i1));

        Order receipt = new Order();
        Order receipt1 = new Order();
        receipt1.add(i2,2);
        receipt.add(new Item("Sai", 0.15), 2);
        receipt.add(new Item("Piim", 0.20), 1);
        receipt.add(i1, 1);
        System.out.println(receipt);
        System.out.println(receipt1);
        Stock tooma = new Stock();

        tooma.receive(receipt);
        System.out.println(tooma);
        System.out.println(tooma.getAvailable(i1));
        Cart tro = new Cart();
        tro.add(i2,2);
        System.out.println(tro.getTotal());
        tro.remove(i2);
        System.out.println(tro.getTotal());
        Order n = tro.checkout();



    }

}
