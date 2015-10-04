package dancers;

/**
 * Created by rapka on 04.10.2015.
 */
public class DancerNode {
    DancerNode left,right;
    int data;
    int height;

    public DancerNode(){
        left = null;
        right = null;
        data = 0;
        height = 0;
    }
    public DancerNode(int n){
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}
