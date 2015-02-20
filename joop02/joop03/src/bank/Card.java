package bank;

/**
 * Created by Ain-Joonas on 20.02.2015.
 */
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Card {
    private BigDecimal amount;
    public Card(){
        this.amount = new BigDecimal(10000.06);
        System.out.println("grabbing balance");
    }


    public void payment(BigDecimal amountToPay){
        System.out.println("Card : payment" + amountToPay);

        this.amount = amount.subtract(amountToPay);
    }
    public BigDecimal getBalance(Calendar Date){
        Calendar date = new GregorianCalendar(Locale.getDefault());
        BigDecimal amount = this.amount;
        System.out.println("Card balance :" + amount);
        return this.amount;
    }
}
