//-----------------------------------------------------------------------
// Empty AVL exception

import java.util.NoSuchElementException;

class EmptyAVLE extends Exception {
}

//-----------------------------------------------------------------------
// Abstract AVL class

abstract class AVL implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyAVLE EAVLX = new EmptyAVLE();

    static AVL EAVL = new EmptyAVL();
    static AVL AVLLeaf(int elem) {
<<<<<<< HEAD
        return new AVLNode(elem,EAVL,EAVL);
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST (AVL avl) {
        try {
            int data = avl.AVLData();
            BST left = toBST(avl.AVLLeft());
            BST right = toBST(avl.AVLRight());
            BST tree = new BSTNode(data, left,right);
            return tree;
        }catch(EmptyAVLE e) {
=======
        return new AVLNode(elem, EAVL, EAVL);
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST(AVL avl) {
        try {
            return new BSTNode(avl.AVLData(), toBST(avl.AVLLeft()), toBST(avl.AVLRight()));
        } catch (EmptyAVLE emptyAVLE) {
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
            return new EmptyBST();
        }
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind(int key);

    abstract AVL AVLinsert(int key);

    abstract AVL AVLeasyRight();

    abstract AVL AVLrotateRight();

    abstract AVL AVLeasyLeft();

    abstract AVL AVLrotateLeft();

    abstract AVL AVLdelete(int key) throws EmptyAVLE;

    abstract Pair<Integer, AVL> AVLshrink() throws EmptyAVLE;

}

//-----------------------------------------------------------------------

class EmptyAVL extends AVL {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLLeft() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLRight() throws EmptyAVLE {
        throw EAVLX;
    }

    int AVLHeight() {
        return 0;
    }

    boolean isEmpty() {
        return true;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
        return false;
    }

    AVL AVLinsert(int key) {
        return AVLLeaf(key);
    }

    AVL AVLeasyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    AVL AVLrotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    AVL AVLeasyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    AVL AVLrotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        throw EAVLX;
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        throw EAVLX;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        return (o instanceof EmptyAVL);
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
}

//-----------------------------------------------------------------------

class AVLNode extends AVL {
    private int data;
    private AVL left, right;
    private int height;

<<<<<<< HEAD
     // constructor
     AVLNode(int data, AVL left, AVL right) {
         this.data = data;
         this.left = left;
         this.right = right;
         this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
     }
=======
    public AVLNode(int data, AVL left, AVL right) {
        this.data = data;
        this.left = left;
        this.right = right;
        height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() {
        return data;
    }

    AVL AVLLeft() {
        return left;
    }

    AVL AVLRight() {
        return right;
    }

    int AVLHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    /**
     * @noinspection Duplicates
     */
    boolean AVLfind(int key) {
<<<<<<< HEAD
        if (data == key) return true;
        else if (key < data) return left.AVLfind(key);
        else return right.AVLfind(key);
    }

    AVL AVLinsert(int key) {
        if(key < this. data) {
            AVL newL = this.left.AVLinsert(key);
            AVL newN = new AVLNode(data, newL, this.right);
            if (newL.AVLHeight() > this.right.AVLHeight()+1) {
                return newN.AVLrotateRight();
            } else {
                return newN;
            }
        }
        else {
            AVL newR = this.right.AVLinsert(key);
            AVL newN = new AVLNode(data, this.left, newR);
            if(newR.AVLHeight() > this.left.AVLHeight() +1) {
                return newN.AVLrotateLeft();
            }
            else {
                return newN;
            }
        }
=======
        boolean found = false;

        if (key == this.data) {
            found = true;
        } else if (key < this.data) {
            found = left.AVLfind(key);
        } else {
            found = right.AVLfind(key);
        }
        return found;
    }

    AVL AVLinsert(int key) {
        AVL b;
        if (key < this.data) {
            AVL newLeft = left.AVLinsert(key);
            b = new AVLNode(data, newLeft, right);

            if (right.AVLHeight()+1 < newLeft.AVLHeight()) {
                b = b.AVLrotateRight();
            }
        } else {
            AVL newRight = right.AVLinsert(key);
            b = new AVLNode(data, left, newRight);
            if (left.AVLHeight()+1 < newRight.AVLHeight() ) {
                b = b.AVLrotateLeft();
            }
        }




        return b;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    AVL AVLeasyRight() {
        try {
<<<<<<< HEAD
            int root = this.left.AVLData();
            AVL LoL = this.left.AVLLeft();
            AVL LoR = this.left.AVLRight();
            return new AVLNode(root, LoL, new AVLNode(this.data, LoR, this.right) );
        }catch (EmptyAVLE e) {
            throw new Error("Internal bug: should never call easyRight on empty tree");
=======
            AVL newRoot = left;
            AVL danglingMan = newRoot.AVLRight();

            return new AVLNode(newRoot.AVLData(), newRoot.AVLLeft(),  new AVLNode(data, danglingMan,right));


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }


    AVL AVLrotateRight() {
        try {
<<<<<<< HEAD
            if ((this.left.AVLLeft().AVLHeight() - this.left.AVLRight().AVLHeight() >= 0)) {
                return AVLeasyRight();
            } else {
                AVL newL = this.left.AVLeasyLeft();
                return new AVLNode(this.data, newL, this.right).AVLeasyRight();
            }
        }catch (EmptyAVLE e) {
            return new EmptyAVL();
        }
=======
            AVL finalTree;
            if (left.AVLLeft().AVLHeight() >= left.AVLRight().AVLHeight()) {
                finalTree = this.AVLeasyRight();
            } else {
                AVL newLeft = left.AVLeasyLeft();
                AVL midwayTree = new AVLNode(data, newLeft, right);
                finalTree = midwayTree.AVLeasyRight();

            }


            return finalTree;
        } catch (EmptyAVLE E) {
            throw new Error ("Why are you rotating something that's empty?");
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    AVL AVLeasyLeft() {
        try {
<<<<<<< HEAD
            int root = this.AVLRight().AVLData();
            AVL RoL = this.right.AVLLeft();
            AVL RoR = this.right.AVLRight();
            return new AVLNode(root, new AVLNode(this.data, this.left, RoL), RoR);
        }catch(EmptyAVLE e) {
            throw new Error("Internal bug: should never call easyRight on empty tree");
=======
            AVL newRoot = right;
            AVL danglingMan = newRoot.AVLLeft();

            return new AVLNode(newRoot.AVLData(), new AVLNode(data, left, danglingMan), newRoot.AVLRight());


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    AVL AVLrotateLeft() {
<<<<<<< HEAD
        try {
            if((this.right.AVLRight().AVLHeight() - this.right.AVLLeft().AVLHeight() >= 0)) {
                return AVLeasyLeft();
            }else {
                AVL newR = this.right.AVLeasyRight();
                return new AVLNode(this.data, this.left,newR).AVLeasyLeft();
            }
        }catch (EmptyAVLE e) {
            return new EmptyAVL();
        }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        if(key<this.data) {
            AVL newL = this.left.AVLdelete(key);
            AVL newN = new AVLNode(data,newL, this.right);

            if(1<this.right.AVLHeight()-newL.AVLHeight()) {
                return newN.AVLrotateLeft();
            } else {
                return newN;
            }
        } else if (this.data < key) {
            AVL newR = this.right.AVLdelete(key);
            AVL newN = new AVLNode(data, this.left, newR);
            if(1<this.left.AVLHeight()-newR.AVLHeight()) {
                return newN.AVLrotateRight();
            } else {
                return newN;
            }
        }
        else {
            // Firstly do shrink on left node. From that I get the largest node of the tree and the balanced remmainder
            if(left.isEmpty()) {
                return right;
            } else {
                Pair<Integer, AVL> newP = left.AVLshrink();
                AVL newN = new AVLNode(newP.getFirst(), newP.getSecond(), right);
                if((1<newN.AVLRight().AVLHeight() - newN.AVLLeft().AVLHeight())) {
                    return newN.AVLrotateLeft();
                }
                return newN;
            }
        }
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        try {
            Pair<Integer, AVL> dl = right.AVLshrink(); // data
            AVL result = new AVLNode(data, left, dl.getSecond());
            if(result.AVLLeft().AVLHeight()-result.AVLRight().AVLHeight()>1) {
                return new Pair(dl.getFirst(),result.AVLrotateRight());
            }
            return new Pair(dl.getFirst(), result);
        } catch (EmptyAVLE e) {
            return new Pair<>(data, left);
        }
=======
       try{
           AVL finalTree;
           if(right.AVLRight().AVLHeight() >= right.AVLLeft().AVLHeight()){
               finalTree = this.AVLeasyLeft();
           }
           else{
               AVL newRight = right.AVLeasyRight();
               AVL midwayTree = new AVLNode(data, left, newRight);
               finalTree = midwayTree.AVLeasyLeft();

           }

           return finalTree;
       }catch(EmptyAVLE e){
           throw new Error("What are you doing rotating something empty?");
       }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        AVL finalTree;

            if (key < data) {
                AVL newLeft = left.AVLdelete(key);
                finalTree = new AVLNode(data, newLeft, right);
                if (newLeft.AVLHeight() + 1 < right.AVLHeight()) {
                    finalTree = finalTree.AVLrotateLeft();
                }

            } else if (key > data) {
                AVL newRight = right.AVLdelete(key);
                finalTree = new AVLNode(data, left, newRight);
                if (newRight.AVLHeight() + 1 < left.AVLHeight()) {
                    finalTree = finalTree.AVLrotateRight();
                }
            } else {
                try {
                    Pair<Integer, AVL> largestElementOnLeftAndBalancedLeft = left.AVLshrink();
                    finalTree = new AVLNode(largestElementOnLeftAndBalancedLeft.getFirst(), largestElementOnLeftAndBalancedLeft.getSecond(), right);
                    if (largestElementOnLeftAndBalancedLeft.getSecond().AVLHeight() + 1 < right.AVLHeight()) {
                        finalTree = finalTree.AVLrotateLeft();
                    }

                } catch (EmptyAVLE e) {
                    //return right if "this" doesnt have a left
                    finalTree = right;
                }
            }


        return finalTree;

    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
       try{
           Pair<Integer, AVL> rightMostChildAndTreeWithoutIt = right.AVLshrink();

           AVL newLeft = new AVLNode(data, left, rightMostChildAndTreeWithoutIt.getSecond());

           if(left.AVLHeight() > rightMostChildAndTreeWithoutIt.getSecond().AVLHeight()+1){
               newLeft = newLeft.AVLrotateRight();
           }
           return new Pair<>(rightMostChildAndTreeWithoutIt.getFirst(), newLeft);

       }catch(EmptyAVLE e){
           return new Pair<Integer,AVL>(data, left);

       }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        if (o instanceof AVLNode) {
            AVLNode other = (AVLNode) o;
            return data == other.data && left.equals(other.left) && right.equals(other.right);
        }
        return false;
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
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
