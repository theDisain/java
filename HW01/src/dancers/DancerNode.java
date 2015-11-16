package dancers;

/**
 * Created by rapka on 15.11.2015.
 */
public class DancerNode {
    IDancer dancer;
    int id;
    int dancerCount;
    int height;
    DancerNode left, right;

    public DancerNode(IDancer dancer, int dancerCount) {
        this.id = dancer.getID();
        this.height = dancer.getHeight();
        this.dancerCount = dancerCount;
        this.dancer = dancer;
    }
}
