package bank;

import java.math.BigDecimal;
import scoring.Scoring;

/**
 * Created by Ain-Joonas on 27.02.2015.
 */
public class Person {
    private int idCode;
    private Card card;
    private CreditCard ccard;
    public Person(int idCode){
        this.idCode = idCode;
        this.card = new Card();
        BigDecimal credLimit = Scoring.getMaxCreditLimit(idCode);

        if(credLimit.compareTo(BigDecimal.ZERO) > 0)
            this.ccard = new CreditCard(credLimit);
    }
}
