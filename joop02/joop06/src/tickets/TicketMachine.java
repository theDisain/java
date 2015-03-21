package tickets;

import java.util.ArrayList;

/**
 * Created by Ain-Joonas on 13.03.2015.
 */
public class TicketMachine {
    private static ArrayList<Ticket> legalTickets = new ArrayList<Ticket>();
    public Ticket buyTicket(String movieName){
        Ticket ticket = new Ticket(movieName);
        legalTickets.add(ticket);
        return ticket;
    }
    public boolean isValid(Ticket ticket){
        for(Ticket t: legalTickets){
            if(ticket.equals(t)) return true;

        }
        return false;
    }
    public void useTicket(Ticket ticket){
        if(!isValid(ticket))System.out.println(ticket.serNumber() + "is not valid");
        else{
            legalTickets.remove(ticket);
            System.out.print("Have fun!");
        }
    }
}
