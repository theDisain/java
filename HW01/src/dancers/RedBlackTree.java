package dancers;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by rapka on 15.11.2015.
 */
public class RedBlackTree {
    private DancerNode root;
    public DancerNode getRootNode() {return this.root;}
    public boolean isEmpty() {return treeSize() == 0;}
    public IDancer getIDancer(int id) {return getDancer(root, id);}
    private ArrayList<IDancer> dancers = new ArrayList<IDancer>();

    @Override
    public String toString() {
        return "RedBlackTree{" +
                "root=" + root +
                ", dancers=" + dancers +
                '}';
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("The tree is empty!");
        root = deleteMin(root);
    }

    public int treeSize() {return treeSize(root);}

    public void insert(IDancer d) {
        if (d == null) return;
        root = insert(root, d);
    }

    private DancerNode insert(DancerNode dancerNode, IDancer dancer) {
        if (dancerNode == null) return new DancerNode(dancer, 1);

        if (dancer.getHeight() < dancerNode.height)
            dancerNode.left = insert(dancerNode.left, dancer);
        else if (dancer.getHeight() >= dancerNode.height)
            dancerNode.right = insert(dancerNode.right, dancer);
        else
            dancerNode.dancer = dancer;
        dancerNode.dancerCount = 1 + treeSize(dancerNode.left) + treeSize(dancerNode.right);
        return dancerNode;
    }

    private int treeSize(DancerNode n) {
        if (n == null) return 0;
        else return n.dancerCount;
    }

    private IDancer getDancer(DancerNode n, int height) {
        if (n == null) return null;

        if (height < n.height)
            return getDancer(n.left, height);
        else if (height > n.height)
            return getDancer(n.right, height);
        else
            return n.dancer;
    }

    private DancerNode deleteMin(DancerNode n) {
        if (n.left == null) return n.right;

        n.left = deleteMin(n.left);
        n.dancerCount = treeSize(n.left) + treeSize(n.right) + 1;
        return n;
    }

    public void deleteDancer(int height) {root = deleteDancer(root, height);}

    private DancerNode deleteDancer(DancerNode dancerNode, int height) {
        if (dancerNode == null) return null;

        if (height < dancerNode.height)
            dancerNode.left  = deleteDancer(dancerNode.left, height);
        else if (height > dancerNode.height)
            dancerNode.right = deleteDancer(dancerNode.right, height);
        else {
            if (dancerNode.right == null) return dancerNode.left;
            if (dancerNode.left  == null) return dancerNode.right;
            DancerNode x = dancerNode;
            dancerNode = getMinDancer(x.right);
            dancerNode.right = deleteMin(x.right);
            dancerNode.left = x.left;
        }
        dancerNode.dancerCount = treeSize(dancerNode.left) + treeSize(dancerNode.right) + 1;
        return dancerNode;
    }


    public void getDancers(DancerNode n) {
        dancers.add(n.dancer);
        if(n.left != null) getDancers(n.left);
        if(n.right != null) getDancers(n.right);
    }

    public int getMinDancer() {
        if (isEmpty()) throw new NoSuchElementException("The tree is empty!");
        return getMinDancer(root).id;
    }

    private DancerNode getMinDancer(DancerNode n) {
        if (n.left == null) return n;
        else return getMinDancer(n.left);
    }

    public ArrayList<IDancer> getDancersList() {
        dancers.clear();
        if(root == null) return this.dancers;

        getDancers(root);return this.dancers;
    }

    public IDancer findPartner(boolean isMale, int dancerHeight) {
        IDancer partner = null;
        DancerNode dancerNode = root;
        if(root == null) return null;

        if(isMale)
        {
            while(dancerNode != null) {
                if(dancerNode.height >= dancerHeight) dancerNode = dancerNode.left;
                else if (dancerNode.height < dancerHeight)
                {
                    if (partner != null)
                    {
                        if (partner.getHeight() < dancerNode.dancer.getHeight()) partner = dancerNode.dancer;
                    }
                    else
                        partner = dancerNode.dancer;

                    dancerNode = dancerNode.right;
                }
            }
        }
        else
        {
            while(dancerNode != null) {
                if(dancerNode.height > dancerHeight) {
                    if (partner != null)
                    {
                        if (partner.getHeight() > dancerNode.dancer.getHeight())
                            partner = dancerNode.dancer;
                    }
                    else
                        partner = dancerNode.dancer;
                    dancerNode = dancerNode.left;
                }
                else if(dancerNode.height <= dancerHeight)
                    dancerNode = dancerNode.right;
            }
        }

        if(partner != null) deleteDancer(partner.getHeight());
        return partner;
    }
}
