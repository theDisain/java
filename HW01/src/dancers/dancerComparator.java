package dancers;

import java.util.Comparator;

public class dancerComparator implements Comparator<IDancer> {
    @Override
    public int compare(IDancer dancer1, IDancer dancer2){
        Dancer d1 = (Dancer) dancer1;
        Dancer d2 = (Dancer) dancer2;
        return d1.compareTo(d2);
    }

}
