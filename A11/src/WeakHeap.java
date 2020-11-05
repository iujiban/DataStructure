import java.util.ArrayList;
import java.util.List;

class RootE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class WeakHeap {
    private int size;
    private ArrayList<Item> elems;
    private ArrayList<Integer> flips;

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    WeakHeap(ArrayList<Item> items) {
        elems = new ArrayList<>();
        flips = new ArrayList<>();
        size = items.size();

        for (int i=0; i<size; i++) {
            Item one = items.get(i);
            one.setPosition(i);
            one.setHeap(this);
            elems.add(one);
            flips.add(0);
        }
        weakHeapify();
    }

    void weakHeapify() {
        try {
            for (int i=size - 1; i>=1; i--) {
                int index = getDAncestorIndex(i);
                join(index, i);
            }
        } catch (RootE rootE) {
           rootE.printStackTrace();
           // throw new Error("Weak heapify");
        }
    }

    // Trivial methods

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    Item findMin() {
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip(int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
        if (i == 0) throw new RootE();
        else return i/2;
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
        if (i == 0) throw new NoLeftChildE();
        int leftInd =  2*i+flips.get(i);
        if (leftInd < size) {
            return leftInd;
        } else {
            throw new NoLeftChildE();
        }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        if (i == 0 ) return 1;
        int rightInd =  2*i+1-flips.get(i);
        if (rightInd < size) {
            return rightInd;
        } else {
            throw new NoRightChildE();
        }
    }

    boolean isLeftChild(int i) throws RootE {
        if (i != 0) {
            if (i % 2 == getFlip(getParentIndex(i))) {
                    return true;
                }
        }
        return false;
    }

    boolean isRightChild(int i) throws RootE {
        if (i != 0) {
            if (i % 2 != getFlip(getParentIndex(i))) {
                    return true;
            }
        }
        return false;
    }

    int getDAncestorIndex(int i) throws RootE {
        if (isRightChild(i) == true) {
            return getParentIndex(i);
        } else {
            return getDAncestorIndex(getParentIndex(i));
        }
    }

    int getLeftMostChildIndex() throws NoLeftChildE {
        if (size <= 1) throw new NoLeftChildE();
        int x = 1;
        try {
            while (true) {
                x = getLeftChildIndex(x);
            }
        } catch (NoLeftChildE e) {
            return x;
        }
    }

    // Helpers for main methods

    /*
    Your swap is incorrect. Here's a way to do it.
    First create two temps containing elems position i and j.
    Next set the ith position of elems to the j temp and vice versa.
    Finally set the positions of the j temp to i and vice versa.
     */
    void swap(int i, int j) {
        Item temp1 = elems.get(i);
        Item temp2 = elems.get(j);

        temp1.setPosition(j);
        temp2.setPosition(i);

        elems.set(i, temp2);
        elems.set(j, temp1);


//        Item one = null;
//        int pos = i;
//        Item two = null;
//        int pos2 = j;

//        one = elems.get(i);
//        two = elems.get(j);
//
//        one.setPosition(pos2);
//        one.setPosition(pos);
//
//        elems.set(i, two);
//        elems.set(j, one);
    }

    boolean join(int i, int j) {
        int temp = elems.get(i).getValue();
        int temp2 = elems.get(j).getValue();
        if (temp > temp2) {
            swap(i, j);
            flips.set(j, 1-flips.get(j));
            return false;
        }
        return true;
    }

    void moveUp(int j) {
        try {
            int index = getDAncestorIndex(j);
            if (join(index, j)) {
                return;
            } else {
                moveUp(index);
            }
        } catch (RootE e) {
            //throw new Error("move up");
        }
    }

    void moveDown(int j) {
        try {
            int index = getLeftMostChildIndex();

            while (j != index) {
                join(j, index);
                index = getParentIndex(index);
            }
        } catch (RootE | NoLeftChildE e) {
            //throw new Error("move down");
        }
    }

    void updateKey(int i, int value) {
        assert value < elems.get(i).getValue();
        // TODO
        this.elems.get(i).setValue(value);
        this.moveUp(i);
    }

    // Main methods: insert and extractMin

    void insert(Item item) {
        try {
            size += 1;
            item.setPosition(size - 1);
            item.setHeap(this);
            elems.add(item);
            flips.add(0);
            if (isLeftChild(size - 1)) {
                flips.set(getParentIndex(size - 1), 0);
            }
            moveUp(size - 1);
        } catch (RootE e) {
           // throw new Error("insert");
        }
    }

    Item extractMin() {
        swap(0, size - 1);
        Item one = elems.get(size - 1);
        elems.remove(size - 1);
        size--;
        if (size > 1) moveDown(0);
        return one;
    }

    // For debugging

    boolean checkOrder() {
        try {
            for (int i = size - 1; i >= 1; i--) {
                int index = getDAncestorIndex(i);
                if (getValue(index) > getValue(i)) {
                    return false;
                }
            }
        } catch (RootE e) {
           // throw new Error ("checkOrder");
        }
        return true;
    }
}


