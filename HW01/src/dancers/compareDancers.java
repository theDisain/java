package dancers;

import java.util.Comparator;

/**
 * Created by rapka on 15.11.2015.
 */
public class compareDancers implements Comparator<IDancer> {
    /**
     * -1 dancer 1 is before dancer 2
     * 0 - dancers have equal priority
     * 1 - dancer 1 is before dancer 2
     */
    @Override
    public int compare(IDancer d1, IDancer d2) {

        if (d1.getHeight() > d2.getHeight())
            return 1;

        else if (d1.getHeight() == d2.getHeight()) {

            if(d1.isMale() && !d2.isMale())
                return -1;
            else if(!d1.isMale() && d2.isMale())
                return 1;
            else
                return 0;

        }

        else
            return -1;

    }
}
