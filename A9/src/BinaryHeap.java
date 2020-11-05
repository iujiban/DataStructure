import java.util.ArrayList;
import java.util.List;

/**
 * Binary heap with reverse bits...
 * We can flip left and right subtrees in one operation
 * <p>
 * There is a subtle interaction between the heap and the items it contains.
 * - the heap maintains an arraylist of all items
 * - each item has a reference to the heap and its position within the arraylist
 */

class NoParentE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class BinaryHeap {
    private int size;
    private ArrayList<Item> elems;

    BinaryHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
    }

    /**
     * This constructor performs "heapify". First it copy the incoming
     * elements one-by-one to the arraylist 'elems' stored as an instance variable.
     * For each item copied, the constructor should initialize properly using
     * setPosition and setHeap. When everything is properly initialized and
     * copied to 'elems' the constructor calls 'heapify'.
     */
    BinaryHeap(ArrayList<Item> es) {
<<<<<<< HEAD
        elems = new ArrayList<>();
        size = es.size();
        Item one;
        // copy the incoming elements one-by-one to the arraylist 'elems'; stored as an instance variable
        //For each item copied, the constructor should initialized properly using setPosition and setHeap
        //Do I have to use the setHeap to initialize?
        for(int i =0; i<size; i++) {
            one = es.get(i);
            one.setPosition(i);
            one.setHeap(this); //automatically set the heap in for loop
            elems.add(one);
        }
        // to call the heapify
=======
        this.size = 0;
        this.elems = new ArrayList<>();
        size = es.size();
        for (int i = 0; i < es.size(); i++) {
            Item old = es.get(i);
            old.setPosition(i);
            old.setHeap(this);
            elems.add(old);
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        heapify();
    }

    /**
     * Implement it as discussed in class...
     */
<<<<<<< HEAD
    void heapify () {
        for (int i = size/2-1; i>=0; i--) { //size is i = this.size/2-1 or just this.size/2
            this.moveDown(i);
=======
    void heapify() {

        for (int i = size / 2 - 1; i >= 0; i--) {
            moveDown(i);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    /**
     * We will location 0 in the array. The minimum is always guaranteed to be there
     * unless of course the array is empty
     */
    Item findMin() {
<<<<<<< HEAD
=======
        // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
        //elems.subList(0, size+1);
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    /**
     * As discussed in class and in the notes, the parent is at index i/2
     * unless of course the current node is the root of the tree
     */
    int getParentIndex(int i) throws NoParentE {
<<<<<<< HEAD
        // parent of i-th node is at (i-1)/2 index
        // this method what profess did
        if (i == 0 ) throw new NoParentE();
        else return (i-1)/2;
//        int parentInd = (i-1)/2;
//        if( i>= 1) {
//            return parentInd;
//        } else {
//            throw new NoParentE();
//        }
=======
        // TODO
        if (i != 0) {
            return (i - 1) / 2;
        }

        throw new NoParentE();
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * Under normal circumstances the left child is at index 2i+1. It is possible
     * the index 2i+1 is outside of the array bounds and in that case the node
     * does not have a left child. It is also possible that the current element
     * has its reverse bit set, which means that the child at index 2i+1 is actually
     * the right child and the child at index 2i+2 is the left child.
     */
    int getLeftChildIndex(int i) throws NoLeftChildE {
<<<<<<< HEAD
        // left child of i-th node is at (2*i+1)th index
        if (i >= size) throw new NoLeftChildE();
        int leftInd = 2*i+1+getElem(i).getRevbit();
        if(leftInd < size) {
            return leftInd;
        } else {
            throw new NoLeftChildE();
        }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        // right child of i-th node is at (2*i+2)th index
        if (i >= size) throw new NoRightChildE();
        int rightInd = 2*i+2-getElem(i).getRevbit();
        if(rightInd < size) {
            return rightInd;
        } else {
            throw new NoRightChildE();
        }
=======
        // check bounds
        // check reverse bit
        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }


        }
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {

        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }
        }

        throw new NoRightChildE();
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * This method swaps the array entries at the given indices. It also needs
     * to update each element with its new position.
     */
    void swap(int i, int j) {
<<<<<<< HEAD
        // how to swap on using the Item one?
        Item one = null;
        int pos = i;
        Item two = null;
        int pos2 = j;
        //get the value from position of the one and two at the arrayList<Item>
        one = elems.get(i);
        two = elems.get(j);
        //swapping the position which it located one <-> two
        one.setPosition(pos2);
        two.setPosition(pos);
        //update each element with its new position
        elems.set(i, two);
        elems.set(j, one);
=======

        Item oldI = this.getElem(i);
        Item oldJ = this.getElem(j);

        //set i to j and j to i
        oldI.setPosition(j);
        oldJ.setPosition(i);

        elems.set(j, oldI);
        elems.set(i, oldJ);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    /**
     * When an element is inserted, it is inserted in the bottom layer of the
     * tree and then moveUp is recursively called to move it to its proper
     * position.
     */

    /*
                    10               Insert (1): Inserted in the bottom layer of the tree (last position of the leaf)
                   / \     ->          10     moveup-> (swapping 1 & 10) 10        recursive
                  20   30             /  \                              /  \       ->(swapping  10 & 1)   1
                  /                 20    30                           1   30                            / \
                40                 / \                                / \                               10  30
                                 40   1                              40  20                             / \
                                                                                                       40 20
             */

    void moveUp(int i) {
        try {
<<<<<<< HEAD
            int j = getParentIndex(i);

            if(getValue(i) < getValue(getParentIndex(i))) { //when the value of i less than parent of the root swap
                swap(i,j); //swap
                moveUp(j); //recursively called to move it
            }
        }catch(NoParentE e) {
=======
            int parentIndex = getParentIndex(i);

            swap(parentIndex, i);

            moveUp(parentIndex);

        } catch (NoParentE e) {

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

        }
    }
    //This method is standing for putting the Item in arrayList
    void insert(Item ek) { //n-1 if it is 10 0 to 9

<<<<<<< HEAD
        size +=1; //increase the original size (es.size) +1
        ek.setPosition(size-1); //setting the position it located. For (size-1), it is starting the index 0 to end 9
        elems.add(ek); // inserting
        moveUp(size-1);
=======
    void insert(Item ek) {

        ek.setPosition(size);
        elems.add(ek);
        moveUp(size);
        size++;

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * When a large element finds itself high in the tree for some reason,
     * we need to move it down. For that we need to compare it to both its
     * children and swap it with the smaller of them
     */
    int minChildIndex(int i) throws NoLeftChildE {

<<<<<<< HEAD
        try {
            if(getValue(getLeftChildIndex(i)) >= getValue(getRightChildIndex(i))) {
                return getRightChildIndex(i);
            }
            else {
                return getLeftChildIndex(i);
            }
        } catch (NoRightChildE e) {
            return getLeftChildIndex(i);
        }
=======
        int leftChildIndex = getLeftChildIndex(i);
        try {
            int rightChildIndex = getRightChildIndex(i);
            if(elems.get(leftChildIndex).getValue()< elems.get(rightChildIndex).getValue()){
                return leftChildIndex;
            }else{
                return rightChildIndex;
            }
        } catch(NoRightChildE e){
            return leftChildIndex;
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    void moveDown(int i) {
        try {
<<<<<<< HEAD
            int j = minChildIndex(i);
            if(getValue(i) > getValue(minChildIndex(i))) {
                swap(i,j);
                moveDown(j);
            }
        }catch (NoLeftChildE e) {
=======
            int minChildIndex = minChildIndex(i);
            if(elems.get(minChildIndex).getValue() < elems.get(i).getValue()) {
                swap(i, minChildIndex);
                moveDown(minChildIndex);
            }
        } catch (NoLeftChildE noLeftChildE) {
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

        }
    }

    /**
     * The minimum is at location 0. To remove it we take the last element
     * in the array and move it to location 0 and then recursively apply
     * moveDown.
     */
    Item extractMin() {
<<<<<<< HEAD
        //swap the minimum which located 0 index
        swap(0,size-1);
        Item one = elems.get(size-1);
        elems.remove(size-1);
        //size is getting decreased when it removed
        size--;
        moveDown(0);
        return one;
=======
        // TODO
        Item min = elems.get(0);
        int lastElementIndex = size-1;
        swap(lastElementIndex,0);
        elems.remove(size-1);
        size--;
        moveDown(0);

        return min;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    public String toString() {
        return getElems().toString();
    }
}
