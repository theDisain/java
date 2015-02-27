package bank;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Ain-Joonas on 20.02.2015.
 */
public class CreditCard extends Card{
    private BigDecimal amount;
    private BigDecimal credLimit;
    private boolean isSpecial = false;


    public CreditCard(BigDecimal credLimit) {

        this.credLimit = credLimit;
    }



    @Override
    public void payment(BigDecimal amountToPay){
        if(isSpecial && amountToPay.longValueExact()>= 50) {
            System.out.println("You poor, son! Over 50 limit");
            return;
        }
        System.out.println("creditCard : payment" + amountToPay);
        if(amountToPay.longValueExact() >= this.amount.longValueExact() && amountToPay.longValueExact() <= this.amount.longValueExact() + this.credLimit.longValueExact() )
            this.amount = amount.subtract(amountToPay);
        if(amountToPay.longValueExact() >= this.amount.longValueExact() + this.credLimit.longValueExact()) {
            this.amount = amount.subtract(amountToPay);
        }
        else
            System.out.println("You poor, son!");

    }
    @Override
    public BigDecimal getBalance(Calendar Date){
        Calendar date = new GregorianCalendar(Locale.getDefault());
        BigDecimal amount = this.amount;
        BigDecimal credLimit = this.credLimit;
        System.out.println("Card balance :" + amount);
        return this.amount;
    }
    public void setSpecial(boolean s){
        this.isSpecial = s;
    }
}
