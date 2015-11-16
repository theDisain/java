package dancers;

import java.util.ArrayList;
import java.util.List;

public class DancerTree {
    private Dancer root;
    protected Dancer bTeam;
    protected int diff;
    Dancer deletedNodeRoot = null;
    public DancerTree() {root = null;}
    public void insert(Dancer node) {insertToTree(this.root,node);}

    public void insertToTree(Dancer root, Dancer newDancer) {
        if (root == null) {
            this.root = newDancer;
        } else {
            if (newDancer.height < root.height) {
                if (root.left == null) {
                    root.left = newDancer;
                    newDancer.parent = root;

                    balanceTreeRecursive(root);
                } else {
                    insertToTree(root.left, newDancer);
                }
            } else if (newDancer.height > root.height) {
                if (root.right == null) {
                    root.right = newDancer;
                    newDancer.parent = root;
                    balanceTreeRecursive(root);
                } else {
                    insertToTree(root.right, newDancer);
                }
            } else if (newDancer.height == root.height) {
                if (!root.isMale() && newDancer.isMale()) {
                    Dancer temp = new Dancer();
                    temp.replaceDancer(root);
                    temp.setEqualHeightDancers(root.getEqualHeightDancers());
                    root.replaceDancer(newDancer);

                    List<Dancer> dancers = temp.getEqualHeightDancers();
                    if (dancers == null) dancers = new ArrayList<>();
                    temp.setInList(true);
                    dancers.add(temp);
                    root.setEqualHeightDancers(dancers);
                    balanceTreeRecursive(root);
                } else {
                    List<Dancer> Dancers = root.getEqualHeightDancers();
                    if (Dancers == null) Dancers = new ArrayList<>();
                    newDancer.setInList(true);
                    Dancers.add(newDancer);
                    root.setEqualHeightDancers(Dancers);
                    balanceTreeRecursive(root);
                }

            }
        }
    }

    public void balanceTreeRecursive(Dancer dancer) {

        setBalance(dancer);
        int balance = dancer.balance;
        if (balance == -2) {
            if (nodeHeight(dancer.left.left) >= nodeHeight(dancer.left.right)) {
                dancer = rotateRight(dancer);
            } else {
                dancer = doubleRotateLeftRight(dancer);
            }
        } else if (balance == 2) {
            if (nodeHeight(dancer.right.right) >= nodeHeight(dancer.right.left)) {
                dancer = rotateLeft(dancer);
            } else {
                dancer = doubleRotateRightLeft(dancer);
            }
        }
        if (dancer.parent != null) {
            balanceTreeRecursive(dancer.parent);
        } else {
            this.root = dancer;
        }
    }
    public void  remove(int val) {
        removeDancer(this.root, val);
    }

    public void removeDancer(Dancer dancer, int val) {
        if (dancer != null) {
            if (dancer.height > val) {
                removeDancer(dancer.left, val);
            }
            else if (dancer.height < val) {
                removeDancer(dancer.right, val);
            }
            else if (dancer.height == val) {
                removeFoundNode(dancer);
            }
        }
    }

    public Dancer eradicateWomen(int height) {
        Dancer copiedResult;
        diff = Integer.MAX_VALUE;
        bTeam = null;
        Dancer back = eradicateWomen(this.root, height);
        if (back != null) {
            copiedResult = new Dancer(back);

        } else {
            copiedResult = back;
        }
        if (deletedNodeRoot != null) {
            deletedNodeRoot.getEqualHeightDancers().remove(back);
        }
        if (bTeam != null) {
            Dancer node = new Dancer();
            List<Dancer> Dancers = bTeam.getEqualHeightDancers();
            if (Dancers != null) {
                for (Dancer d : Dancers) {
                    if (d.isMale()) {
                        node.replaceDancer(d);
                        Dancers.remove(d);
                        break;
                    }
                    if (node.getHeight() == 0) {
                        Dancer dd = Dancers.get(0);
                        node.replaceDancer(dd);
                        Dancers.remove(dd);
                    }
                    List<Dancer> dancers1 = new ArrayList<>();
                    for (Dancer Dancer : Dancers) {
                        dancers1.add(Dancer);
                    }
                    node.setEqualHeightDancers(dancers1);
                    removeFoundNode(bTeam);
                    insert(node);
                }
            } else {
                removeFoundNode(bTeam);
            }
        }
        return copiedResult;
    }

    public Dancer eradicateWomen(Dancer Dancer, int maleHeight) {
        if (Dancer == null) {
            return bTeam;
        }
        if (Dancer.getHeight() >= maleHeight) {
            return eradicateWomen(Dancer.left, maleHeight);
        } else if (Dancer.getHeight() < maleHeight) {
            if (Dancer.isMale()) {
                try {
                    List<Dancer> Dancers = Dancer.getEqualHeightDancers();
                    for (Dancer d : Dancers) {
                        int newDiff = Math.abs(d.getHeight() - maleHeight);
                        if (!d.isMale() && newDiff < diff && newDiff != 0) {
                            deletedNodeRoot = Dancer;
                            bTeam = d;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                    Dancer temp = eradicateWomen(Dancer.right, maleHeight);
                    if (temp == null) {
                        return eradicateWomen(Dancer.left, maleHeight);
                    } else {
                        return temp;
                    }

                }
            } else {
                int newDiff = Math.abs(Dancer.getHeight() - maleHeight);
                if (newDiff < diff && newDiff != 0) {
                    bTeam = Dancer;
                    diff = newDiff;
                }
            }
            return eradicateWomen(Dancer.right, maleHeight);
        }
        return bTeam;
    }
    public Dancer eradicateMen(int height) {
        Dancer copiedResult;
        bTeam = null;
        diff = Integer.MAX_VALUE;
        Dancer back = eradicateMen(this.root, height);
        if (back != null) {
            copiedResult = new Dancer(back);

        } else {
            copiedResult = back;
        }
        if (deletedNodeRoot != null) {
            deletedNodeRoot.getEqualHeightDancers().remove(back);
        }
        if (bTeam != null) {
            Dancer node = new Dancer();
            List<Dancer> Dancers = bTeam.getEqualHeightDancers();

            if (Dancers != null) {
                removeFoundNode(bTeam);
                for (int i = 0; i < Dancers.size(); i++) {
                    Dancer d = Dancers.get(i);
                    if (d.isMale()) {
                        node.replaceDancer(d);
                        Dancers.remove(d);
                        break;
                    }
                    if (node.getHeight() == 0) {
                        Dancer dd = Dancers.get(0);
                        node.replaceDancer(dd);
                        Dancers.remove(dd);
                    }
                    List<Dancer> dancers1 = new ArrayList<>();
                    for (Dancer Dancer : Dancers) {
                        dancers1.add(Dancer);
                    }
                    node.setEqualHeightDancers(dancers1);
                    insert(node);
                }


            } else {
                removeFoundNode(bTeam);

            }
        }

        return copiedResult;
    }
    public Dancer eradicateMen(Dancer dancer, int femaleHeight) {
        if (dancer == null) {
            return bTeam;
        }

        if (dancer.getHeight() > femaleHeight) {
            if (!dancer.isMale()) {
                try {
                    List<Dancer> Dancers = dancer.getEqualHeightDancers();
                    for (Dancer d : Dancers) {
                        int newDiff = Math.abs(d.getHeight() - femaleHeight);
                        if (d.isMale() && newDiff < diff && newDiff != 0) {
                            bTeam = d;
                            deletedNodeRoot = dancer;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                }

            } else {
                int newDiff = Math.abs(dancer.getHeight() - femaleHeight);
                if (newDiff < diff && newDiff != 0) {
                    bTeam = dancer;
                    diff = newDiff;
                }
            }
            return eradicateMen(dancer.left, femaleHeight);
        } else if (dancer.getHeight() <= femaleHeight) {
            return eradicateMen(dancer.right, femaleHeight);
        }
        return bTeam;
    }
    public void removeFoundNode(Dancer dancer) {
        Dancer lastElement;
        if (dancer.left == null || dancer.right == null) {
            if (dancer.parent == null) {
                if (!dancer.isInList()) {
                    if (dancer.left != null) {
                        this.root = dancer.left;
                    } else if (dancer.right != null) {
                        this.root = dancer.right;
                    } else {
                        this.root = null;
                    }
                }
                return;
            }
            lastElement = dancer;
        } else {
            lastElement = successor(dancer);
            dancer.height = lastElement.height;
        }
        Dancer p;
        if (lastElement.left != null) {
            p = lastElement.left;
        } else {
            p = lastElement.right;
        }
        if (p != null) {
            p.parent = lastElement.parent;
        }
        if (lastElement.parent == null) {
            this.root = p;
        } else {
            if (lastElement == lastElement.parent.left) {
                lastElement.parent.left = p;
            } else {
                lastElement.parent.right = p;
            }
            balanceTreeRecursive(lastElement.parent);
        }
    }
    public Dancer rotateLeft(Dancer dancerOriginal) {

        Dancer dancerRotater = dancerOriginal.right;
        dancerRotater.parent = dancerOriginal.parent;

        dancerOriginal.right = dancerRotater.left;
        if (dancerOriginal.right != null) {
            dancerOriginal.right.parent = dancerOriginal;
        }
        dancerRotater.left = dancerOriginal;
        dancerOriginal.parent = dancerRotater;

        if (dancerRotater.parent != null) {
            if (dancerRotater.parent.right == dancerOriginal) {
                dancerRotater.parent.right = dancerRotater;
            } else if (dancerRotater.parent.left == dancerOriginal) {
                dancerRotater.parent.left = dancerRotater;
            }
        }
        setBalance(dancerOriginal);
        setBalance(dancerRotater);

        return dancerRotater;
    }
    public Dancer rotateRight(Dancer dancerOriginal) {

        Dancer dancerRotater = dancerOriginal.left;
        dancerRotater.parent = dancerOriginal.parent;

        dancerOriginal.left = dancerRotater.right;
        if (dancerOriginal.left != null) {
            dancerOriginal.left.parent = dancerOriginal;
        }
        dancerRotater.right = dancerOriginal;
        dancerOriginal.parent = dancerRotater;

        if (dancerRotater.parent != null) {
            if (dancerRotater.parent.right == dancerOriginal) {
                dancerRotater.parent.right = dancerRotater;
            } else if (dancerRotater.parent.left == dancerOriginal) {
                dancerRotater.parent.left = dancerRotater;
            }
        }
        setBalance(dancerOriginal);
        setBalance(dancerRotater);
        return dancerRotater;
    }

    public Dancer doubleRotateLeftRight(Dancer dancer) {
        dancer.left = rotateLeft(dancer.left);
        return rotateRight(dancer);
    }
    public Dancer doubleRotateRightLeft(Dancer dancer) {
        dancer.right = rotateRight(dancer.right);
        return rotateLeft(dancer);
    }
    public Dancer successor(Dancer dancer) {
        if (dancer.right != null) {
            Dancer r = dancer.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else {
            Dancer p = dancer.parent;
            while (p != null && dancer == p.right) {
                dancer = p;
                p = dancer.parent;
            }
            return p;
        }
    }
    private int nodeHeight(Dancer dancer) {
        if (dancer == null) {
            return -1;
        }
        if (dancer.left == null && dancer.right == null) {
            return 0;
        } else if (dancer.left == null) {
            return 1 + nodeHeight(dancer.right);
        } else if (dancer.right == null) {
            return 1 + nodeHeight(dancer.left);
        } else {
            return 1 + Math.max(nodeHeight(dancer.left), nodeHeight(dancer.right));
        }
    }
    private void setBalance(Dancer dancer) {
        dancer.balance = nodeHeight(dancer.right) - nodeHeight(dancer.left);
    }
    final protected List<IDancer> putInOrder() {
        ArrayList<IDancer> returnedDancers = new ArrayList<>();
        putInOrder(root, returnedDancers);
        return returnedDancers;
    }
    final protected void putInOrder(Dancer n, ArrayList<IDancer> inOrder) {
        if (n == null) {
            return;
        }
        putInOrder(n.left, inOrder);
        inOrder.add(n.getiDancer());

        try {
            List<Dancer> Dancers = n.getEqualHeightDancers();
            if (Dancers.size() > 0) {
                for (Dancer d : Dancers) {
                    inOrder.add(d.getiDancer());
                }
            }

        } catch (Exception e) {
        }
        putInOrder(n.right, inOrder);
    }

    @Override
    public String toString() {
        return "DancerTree{" +
                "root=" + root +
                '}';
    }
}
