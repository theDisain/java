package booth;

import tickets.Ticket;
import tickets.TicketMachine;

/**
 * Created by Ain-Joonas on 13.03.2015.
 */
public class TicketTest {

    public static void main(String[] args) {
        TicketMachine m1 = new TicketMachine();
        Ticket t1 = m1.buyTicket("Whiplash");
        TicketMachine m2 = new TicketMachine();
        Ticket t2 = m1.buyTicket("Whiplash");
        System.out.println(m1.isValid(t2));
        m1.useTicket(t1);

    }

}
