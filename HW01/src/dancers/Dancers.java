package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Dancers implements IDancers {

    private DancerTree men = new DancerTree();
    private DancerTree women = new DancerTree();

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer d)
            throws IllegalArgumentException {
        Dancer dancer = new Dancer(d.getHeight(),d.isMale());
        if(dancer.isMale()){
            Dancer partner = women.getPartner(dancer);
            if(partner == null)
                men.addDancer(dancer);
            else
                return new SimpleEntry<IDancer, IDancer>(dancer,partner);
        }
        else if(!dancer.isMale()){
            Dancer partner = men.getPartner(dancer);
            if(partner == null)
                women.addDancer(dancer);
            else return new SimpleEntry<IDancer, IDancer>(dancer,partner);
        }
        else throw new IllegalArgumentException();
        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        // TODO Auto-generated method stub
        return null;
    }

}
