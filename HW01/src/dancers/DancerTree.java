package dancers;

public class DancerTree {
    private Dancer root;

    public DancerTree() {
        root = null;
    }
    public Dancer getPartner(Dancer dancer){
        return null;
    }
    public void addDancer(Dancer dancer){
        addDancer(dancer);
    }
    public Dancer addDancer(int x, Dancer dancer){
        if(dancer == null)
            dancer = new Dancer();
        else if(x < dancer.getHeight()) {
            dancer.left = addDancer(x, dancer.left);
            if (dancer.left.getHeight() - dancer.right.getHeight() == 2 ){
                if(x < dancer.left.getHeight())
                    dancer = rotateWithLeftChild(dancer);
                else
                    dancer = rotateWithRightChild(dancer);
            }
        }
        return dancer;
    }

    private Dancer insert(int x, Dancer node) {
        if (node == null)
            node = new Dancer();
        else if (x < node.treeHeight) {
            node.left = insert(x, node.left);
            if (height(node.left) - height(node.right) == 2) {
                if (x < node.left.treeHeight)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
            }
        }
        else if (x > node.getHeight())

        {

            node.right = insert(x, node.right);
            if (height(node.right) - height(node.left) == 2)
                if (x > node.right.getHeight())
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);

        } else   // Duplicate; do nothing
            node.treeHeight = max(height(node.left), height(node.right)) + 1;

        return node;

    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private int height(Dancer node) {
        return node == null ? -1 : node.treeHeight;
    }

    private int max(int left,int right){
        return left > right ? left : right;
    }

    private Dancer rotateWithLeftChild(Dancer leftNode){
        Dancer rightNode = leftNode.left;
        leftNode.left = rightNode.right;
        rightNode.right = leftNode;
        leftNode.treeHeight = max(height(leftNode.left),height(leftNode.right)) + 1;
        return leftNode;
    }
    private Dancer doubleWithLeftChild(Dancer node){
       node.left = rotateWithLeftChild(node.left);
       return rotateWithLeftChild(node);
   }

    private Dancer rotateWithRightChild(Dancer rightNode){
        Dancer leftNode = rightNode.right;
        rightNode.right = leftNode.left;
        leftNode.left = rightNode;
        rightNode.treeHeight = max(height(rightNode.left),height(rightNode.right)) + 1;
        leftNode.treeHeight = max(height(leftNode.right),rightNode.treeHeight) + 1;
        return leftNode;
    }
    private Dancer doubleWithRightChild(Dancer node){
       node.right = rotateWithLeftChild(node.right);
       return rotateWithRightChild(node);
   }
    private int countNodes(Dancer node){
        if(node == null)
            return 0;
        else{
            int count = 1;
            count += countNodes(node.left);
            count += countNodes(node.right);
            return count;
        }
    }
    public int countNodes(){
        return countNodes(root);
    }
    private boolean search(Dancer node, int val){
        boolean found = false;
        while((node != null) && !found){
            int rval = node.getHeight();
            if(val < rval){
                node = node.left;
            }
            else if(val > rval)
                node = node.right;
            else{
                found = true;
                break;
            }
            found = search(node, val);

        }
        return found;
    }
}
