package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rapka on 15.11.2015.
 */
public class Dancers implements IDancers {

    private RedBlackTree men = new RedBlackTree();
    private RedBlackTree women = new RedBlackTree();

    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer dancer)
            throws IllegalArgumentException {
        IDancer partner;

        if (dancer.getHeight() <= 0) throw new IllegalArgumentException();

        if (dancer.isMale()) {
            partner = women.findPartner(dancer.isMale(), dancer.getHeight());
            if (partner == null) {
                men.insert(dancer);
                return null;
            }
        } else {
            partner = men.findPartner(dancer.isMale(), dancer.getHeight());
            if (partner == null) {
                women.insert(dancer);
                return null;
            }
        }
        return new SimpleEntry<IDancer, IDancer>(dancer, partner);
    }

    @Override
    public List<IDancer> returnWaitingList() {
        List<IDancer> list = new ArrayList<IDancer>() {{
            addAll(men.getDancersList());
            addAll(women.getDancersList());
        }};
        list.sort(Dancer.compareHeight);
        return list;
    }
    public String returnDancersList(RedBlackTree tree) {
        return tree.toString();
    }
    public RedBlackTree getMen() {return men;}
    public RedBlackTree getWomen() {return women;}
}