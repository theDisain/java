package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public interface IDancers {
    public SimpleEntry<IDancer,IDancer> findPartnerFor(IDancer d)
        throws IllegalArgumentException;

    /*
     * Returns waiting list as an array (both men and women)
     * Ordered shortest --> longest
     * If man and woman are having the same height,
     * then ordering should be man, woman
     */
    public List<IDancer> returnWaitingList();

}