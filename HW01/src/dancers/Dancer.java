package dancers;

import java.util.Comparator;

/**
 * Created by rapka on 15.11.2015.
 */
public class Dancer implements IDancer{
    private int unikaalne_id;
    private boolean sugu;
    private int pikkus;

    @Override
    public String toString() {
        return "Dancer{" +
                "unikaalne_id=" + unikaalne_id +
                ", onMees=" + sugu +
                ", pikkus=" + pikkus +
                '}';
    }

    @Override
    public int getID() {return unikaalne_id;}
    @Override
    public boolean isMale() {return sugu;}
    @Override
    public int getHeight() {return pikkus;}

    public Dancer(int id, boolean isMale, int height) {
        this.unikaalne_id = id;
        this.sugu = isMale;
        this.pikkus = height;
    }
    public Dancer(IDancer d) {
        this.unikaalne_id = d.getID();
        this.sugu = d.isMale();
        this.pikkus = d.getHeight();
    }


    public static Comparator<IDancer> compareHeight = new Comparator<IDancer>() {
        @Override
        public int compare(IDancer d1, IDancer d2) {
            if (d1.getHeight() > d2.getHeight()) return 1;
            else if (d1.getHeight() < d2.getHeight()) return -1;
            else {
                if ((d1.isMale()) && (!d2.isMale())) return -1;
                else if ((!d1.isMale()) && (d2.isMale())) return 1;
                else return 0;
            }
        }};
}
