package dancers;

import java.util.AbstractMap;
import java.util.List;

/**
 * Created by rapka on 15.11.2015.
 */
public interface IDancers {
    public AbstractMap.SimpleEntry<IDancer,IDancer> findPartnerFor(IDancer d)
            throws IllegalArgumentException;

    public List<IDancer> returnWaitingList();
}
