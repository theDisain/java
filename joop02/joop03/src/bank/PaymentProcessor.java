package bank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ain-Joonas on 20.02.2015.
 */
public class PaymentProcessor {
    public void process(List<Card> cards){
        cards.forEach(card->card.payment(new BigDecimal(10000)));

    }
}
