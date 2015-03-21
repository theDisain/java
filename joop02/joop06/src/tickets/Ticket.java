package tickets;

/**
 * Created by Ain-Joonas on 13.03.2015.
 */
public class Ticket {
    private String movieName;
    private int serialNumber;
    protected static int counter = 0;

    public Ticket(String movieName) {
        super();
        this.movieName = movieName;
        serialNumber = counter++;
    }

    @Override
    public String toString() {
        return "#" + serialNumber + "\t" + movieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (serialNumber != ticket.serialNumber) return false;
        if (!movieName.equals(ticket.movieName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return movieName.hashCode();
    }
    public String serNumber(){

        return "#" + serialNumber;
    }
}
