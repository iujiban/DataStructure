import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

//-----------------------------------------------------------------------
// Empty BST exception

class EmptyBSTE extends Exception {}

//-----------------------------------------------------------------------
// Abstract BST class

abstract class BST implements TreePrinter.PrintableNode, Iterable<Integer> {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyBSTE EBSTX = new EmptyBSTE();

    static BST EBST = new EmptyBST();

    // A leaf is a tree with empty left and right children
    static BST BSTLeaf(int elem) {
<<<<<<< HEAD
        return new BSTNode(elem, EBST, EBST);
         }
=======
        return new BSTNode(elem, EBST, EBST); // TODO
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    // Use the iterator (that you need to define below) to get the BST nodes
    // one-by-one and insert them into the resulting AVL tree.
    // for eachlopp insert BST to AVL one by one.
    static AVL toAVL (BST bst) {
<<<<<<< HEAD
        AVL AVLtree = AVL.EAVL;
        for (int elem: bst) {
            AVLtree = AVLtree.AVLinsert(elem);
        }
        return AVLtree; // TODO
=======

        try {
            Iterator<Integer> bstIterator = bst.iterator();
            AVLNode avl = new AVLNode(bstIterator.next(), new EmptyAVL(), new EmptyAVL());
            while (bstIterator.hasNext()) {
                avl.AVLinsert(bstIterator.next());
            }
            return avl;
        }catch(EmptyStackException e){
            return AVL.EAVL;
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int BSTData() throws EmptyBSTE;

    abstract BST BSTLeft() throws EmptyBSTE;

    abstract BST BSTRight() throws EmptyBSTE;

    abstract int BSTHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean BSTfind (int key);

    abstract BST BSTinsert(int key);

    abstract BST BSTdelete(int key) throws EmptyBSTE;

    abstract Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE;

    abstract BST flip();
}

//-----------------------------------------------------------------------

class EmptyBST extends BST {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int BSTData() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTLeft() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTRight() throws EmptyBSTE {
        throw EBSTX;
    }

    int BSTHeight() {
        return 0;
    }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        return false;
    }

    BST BSTinsert(int key) {
        return BSTLeaf(key);
    }

    BST BSTdelete(int key) throws EmptyBSTE { throw EBSTX; }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE { throw EBSTX; }

    @Override
    BST flip() {
        return this;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() {
                return false;
            }

            public Integer next() {
                throw new NoSuchElementException();
            }
        };
    }
}

//-----------------------------------------------------------------------
// Non-empty tree case

class BSTNode extends BST {
    private int data;
    private BST left, right;
    private int height;

    // constructor
    BSTNode(int data, BST left, BST right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.BSTHeight(), right.BSTHeight());
    }

    public BSTNode(int data, BST left, BST right){
        this.data = data;
        this.left = left;
        this.right = right;
        height = Math.max(left.BSTHeight(),right.BSTHeight())+1;
    }

    int BSTData() throws EmptyBSTE {
        return data;
    }

    BST BSTLeft() throws EmptyBSTE {
        return left;
    }

    BST BSTRight() throws EmptyBSTE {
        return right;
    }

    int BSTHeight() {
<<<<<<< HEAD
        return height;
=======
        return height; // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Main methods
    //--------------------------
    /*
            5      <- root      example find (7)     We should look at the first root subtree (data, left, right)
          /   \                      5
         2     10                   /  \
          \    / \                2     10          Nothing in here find another one (data, left, right) and it is right
           4  7   12                                subtree because 7 is greater 5 root has.
                                                    10
                                                    / \
                                                   7   12
                                                  this subtree has data which we want so that return the true
     */
    boolean BSTfind(int key) {
<<<<<<< HEAD
        if (data == key) return true;
        else if (data > key) return left.BSTfind(key); // if the key is lesser than data, going to the left subtree
        else return right.BSTfind(key);// if the key is greater than data, going to the right subtree
=======

        boolean found = false;
        if (key == this.data){
            found = true;
        }
        else if(key<this.data){
            found = left.BSTfind(key);
        }
        else{
            found = right.BSTfind(key);
        }

        return found;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /** @noinspection Duplicates*/
    BST BSTinsert(int key) {
<<<<<<< HEAD
        if (data > key) {
            return new BSTNode(data, left.BSTinsert(key), right);
        }
        else {
            return new BSTNode(data, left, right.BSTinsert(key));
        }
    }

    /*
     example: BSTdelete within using the BSTdeleteLeftMostLeaf
                 10                 BSTdelete(15)
                /   \               find the key 15
               5     15                 15
             /  \    /   \             /  \
            2    7  12    17          12   17
          /  \  / \      /  \              / \
          1  3 6   8     16  18           16  18                  right.BSTdeleteLeftMost()
                                               15             16                16
                                                 \              \                 \
                                                  17    ->       17       ->       17
                                                 /  \           15 18                18
                                                16  18  finding the smallest value from the (15) right subtree. -> 16
                                                        swapping 15 and 16 delete the value which we want(15)

     */
    BST BSTdelete(int key) throws EmptyBSTE {
=======

        BSTNode b;
        if(key<this.data){
           b = new BSTNode(data,left.BSTinsert(key),right);
        }
        else{
           b = new BSTNode(data, left, right.BSTinsert(key));
        }

return b;
    }



    /** @noinspection Duplicates*/
    BST BSTdelete(int key) throws EmptyBSTE {
        if (key == this.data){
            try {
                Pair<Integer, BST> leftMostChildOnRightAndTreeWithoutIt = right.BSTdeleteLeftMostLeaf();
                return new BSTNode(leftMostChildOnRightAndTreeWithoutIt.getFirst(), left, leftMostChildOnRightAndTreeWithoutIt.getSecond());
            }catch(EmptyBSTE e){
                return left;
            }
        }
        else if(key<this.data){
            return new BSTNode(data, left.BSTdelete(key),right);
        }
        else{
            return new BSTNode(data, left,right.BSTdelete(key));
        }
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

        if (key < data) return new BSTNode(data, left.BSTdelete(key), right);
        else if (key > data) return new BSTNode(data, left, right.BSTdelete(key));
        else {
            try {
                Pair<Integer, BST> dr = right.BSTdeleteLeftMostLeaf();
                return new BSTNode(dr.getFirst(),left, dr.getSecond());
            } catch (EmptyBSTE e) {
                return left;
            }
        }
    }
    /*
    finding the smallest element of a tree, and returning a pair of that element and the resulting tree with
    that element removed
     */
    Pair<Integer, BST> BSTdeleteLeftMostLeaf() {
<<<<<<< HEAD
        try {
            Pair<Integer, BST> dl = left.BSTdeleteLeftMostLeaf();
            return new Pair<>(dl.getFirst(),new BSTNode(data, dl.getSecond(), right));
        }
        catch (EmptyBSTE e) {
            return new Pair<>(data, right);
        }
=======
        try{
            Pair<Integer, BST> alpha = left.BSTdeleteLeftMostLeaf();
            return new Pair<Integer,BST>(alpha.getFirst(),new BSTNode(data, alpha.getSecond() ,right));
        }catch(EmptyBSTE e){
            return new Pair<>(data, right);
        }
    }

    @Override
    BST flip() {
        return new BSTNode(data, right.flip(), left.flip())
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return String.valueOf(data);
    }

    //--------------------------
    // Iterable interface
    //--------------------------
    // in-order-traversal (left, root, right)
    public Iterator<Integer> iterator() {
<<<<<<< HEAD

        return new Iterator<Integer>() {

            //instance variable
            private boolean hasNext = true;
            private Iterator<Integer> leftlter = left.iterator(); //check the left , if left hasNext, then return left.next()
            private Iterator<Integer> rightlter = right.iterator(); //check the right, if right hasNext, then return right.next()
            private boolean visited = false;
            //hasNext() function returns a boolean value indicating whether there are any more elements left in the
            //binary search tree or not.
            public boolean hasNext() {
                return hasNext;
            }
            //Implementation in-order-traversal left subtree, right subtree
            // vistor ==> boolean left, right


            // next() function returns the next samllest element in the BST. when we call next() for the very last time,
            // it should return the largest element in the BST
            //visiting node if not visiting equaled to the false

            // how many cases do we need on this one to build up the in order traversal?
            // using the while loop to
            public Integer next() {
                if (leftlter.hasNext()) {
                    return leftlter.next();
                }
                if (visited == false) {
                    visited = true;
                    hasNext = rightlter.hasNext();
                    return data;
                }
                if (rightlter.hasNext()) {
                    int next = rightlter.next();
                    hasNext = rightlter.hasNext();
                    return next;
                }
                throw new NoSuchElementException();
            }
        };
=======
        return new TreeIterator(this);
    }


}

class TreeIterator implements Iterator<Integer>{


    private Stack<BST> stack = new Stack<>();

    public TreeIterator(BST b){
        loadStack(b);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }




    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        try {
            BST s = stack.pop();
            int t = 0;
            try {
                t = s.BSTData();
                this.loadStack(s.BSTRight());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
            return t;
        } catch (EmptyStackException e){
            throw new NoSuchElementException();
        }

    }

    private void loadStack(BST root){
        if (!root.isEmpty()){
            stack.push(root);
            try {
                loadStack(root.BSTLeft());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
        }
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
