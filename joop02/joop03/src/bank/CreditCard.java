package bank;

import java.math.BigDecimal;

/**
 * Created by Ain-Joonas on 20.02.2015.
 */
public class CreditCard extends Card{
    private BigDecimal amount;


    public CreditCard(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }
    @Override
    public void payment(BigDecimal amountToPay){
        System.out.println("creditCard : payment" + amountToPay);

        this.amount = amount.subtract(amountToPay);

    }
}
