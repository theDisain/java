package bank;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Ain-Joonas on 20.02.2015.
 */
public class BankTest {
    public static void main(String[] args){
        Card credit1 = new CreditCard(new BigDecimal(10000));
        Card card = new Card();
        card.payment(new BigDecimal(10.55));
        Calendar date = new GregorianCalendar(Locale.getDefault());
        card.getBalance(date);
        List<Card> cards = new ArrayList<Card>();
        cards.add(card);
        PaymentProcessor processor = new PaymentProcessor();
        processor.process(cards);
    }
}
