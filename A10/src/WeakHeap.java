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
    /*
     * Position 0 in elems is for the root.
     * The root has no left child.
     * Position 1 in elems is for the right child of the root
     * After that the left child of an item at position i is at position 2i+flips(i)
     * and the right child is at position 2i+1-flips(i)
     * The parent of a child at position i is at position i/2
     * flips(0) should never be set to 1 because that would give the root
     * a left child instead of a right one
     */

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    /*
     * The following constructor does the following sequences of steps:
     *   - it copies the incoming items to a the array items
     *   - for each item, setPosition and setHeap are called with the
     *     appropriate parameters
     *   - for each item, a corresponding flip bit is initialized to 0
     *   - call weakHeapify
     */
    WeakHeap (ArrayList<Item> items) {
        elems = new ArrayList<>();
        flips = new ArrayList<>();
        size = items.size();
        //Item one = new Item(0);
	// TODO
        // copies the incoming items to a the array items for each item, setPosition and setHeap are called with
        //the appropriate paremeters
        for (int i =0; i<size; i++) {
            Item one = items.get(i);
            one.setPosition(i);
            one.setHeap(this);
            elems.add(one);
            //for each item, a coressponding flip bit is initialized to 0
            flips.add(0);
        }
        //to call the weakHeapify
        weakHeapify();
    }

    /*
     * This method executes a loop that starts with the last element
     * in the array and ends with the element at position 1. In each
     * iteration, the item is joined with its distinguished ancestor. 
     * Note that when calling join, the distinguished ancestor is 
     * always in the first argument position. 
     */
    void weakHeapify () {
        //a loop that starts with the last element in the array and ends with the element at position 1;
        try {
            for (int i = size - 1; i >= 1; i--) { // starts with the last element in the array and ends with the element at pos 1.
                int index = getDAncestorIndex(i);
                join(index, i);
                }
            } catch (RootE rootE) {
                rootE.printStackTrace();
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

    Item getElem (int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip (int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
	if (i == 0 ) throw new RootE();
	else return i/2;
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
	if (i >= size || i == 0) throw new NoLeftChildE() ;
	int leftInd = 2*i+flips.get(i); //getElem(i).getRevibt() -> where to get this one
        if (leftInd < size) {
            return leftInd;
        } else {
            throw new NoLeftChildE();
        }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
	if (i>= size) throw new NoRightChildE();
	int rightInd = 2*i+1-flips.get(i); //getElem(i).getRevibit();
        if (rightInd < size) {
            return rightInd;
        } else {
            throw new NoRightChildE();
        }
    }
    /*
    If reverseBit => 0
    when you write even i => i % 2 ==0 and reverseBit i % 2 == 0 it is leftChild
     */
    boolean isLeftChild (int i) throws RootE {
        if (i != 0) {
            if (i % 2 == 0) {
                if (getFlip(getParentIndex(i)) == 0) {
                    return true;
                }
            } else if (i % 2 == 1) {
                if (getFlip(getParentIndex(i)) == 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /*
    If reverseBit => 1
    when you write odd i => i % 2 ==1 and reverseBit i % 2 == 1 it is rightChild
     */

    boolean isRightChild (int i) throws RootE {
        // TODO
        if (i != 0) {
            if (i % 2 == 1) {
                if (getFlip((getParentIndex(i))) == 0) {
                    return true;
                }
            } else {
                if (getFlip(getParentIndex(i)) == 1) {
                    return true;
                } return false;
            }
        }
        return false;
    }

    /*

    getDancestor(i) = if rightChild(i)
    return parent(i)
    else return getDancestor (parent (it)) recursive call.

     */
    int getDAncestorIndex(int i) throws RootE {
            if (isRightChild(i) == true) {
                return getParentIndex(i);
            } else {
                return getDAncestorIndex(getParentIndex(i));
            }
    }
    /*
        General structure:
        if size <= 1; throw exception
        Otherwise, we have heap with a left-most child

        Get left most child index. (We are looking along the left side of the right child of the root)
        3
          \
             9
           /   \
          6     15
         / \    /
        8   7 11

        11                   Step 0 : store the minimum
          \                  Step 1 : Replace the first item with last item
             9
           /   \
          6     15
         / \
        8   7

        8                    Step2: Compare the root against each of the nodes on the left side of its right child.
          \                         if the child is smaller than the root, swap, and then flip the bit of the child
             9                      ! means flipped bit
           /   \
          6     15
         / \
       11!   7

         6                   7 is not greater than 8 Let's flip
          \
             9
           /   \
          8!     15
         / \
       11!  7


         6
          \
             9
           /   \
          8     15
         / \
       7    11              Now return the minimum we stored in Step0, so return 3.

        Special thing about the root is that it does not have a left child

        Otherwise, we have with a left-most child
        3       8--> left-most child
         \
          8
        In the example above, 8 is the left most child.
        So we should start at index 1.

        Start at 1, and get its left child, Our left most is now updated to that.
        Then we want to the left of that, and we keep doing this until we cannot go left anymore.
        Once we are unable to go left, we have found our left most child.
        3
         \
          8
         / \
        6   10
        In the example above, 8 is the left most.
        so we should start at index 1.
     */
    int getLeftMostChildIndex () throws NoLeftChildE {
        if (size <= 1) throw new NoLeftChildE();
        // store the minimum
        int minmum = findMin().getValue();
        int x = 1;
      //  Replace the first item (index: 0 is the first item) and last item (index: n-1 is the last element of the item)

      //  swap(getValue(0), getValue(size-1));

        // Compare the root against each of the nodes on the left side of its right child.
        // if the child is smaller than the root, swap, and then flip the bit of the child
        // getValue(index: 0) == root
        try {
            while (true) {
                x = getLeftChildIndex(x);
            }
        } catch (NoLeftChildE e) {
            return x;
        }
        // return the minimum we stored

    }

    // Helpers for main methods

    void swap(int i, int j) {
        Item one = null;
        int pos = i;
        Item two = null;
        int pos2 = j;

        one = elems.get(i);
        two = elems.get(j);

        one.setPosition(pos2);
        one.setPosition(pos);

        elems.set(i, two);
        elems.set(j, one);
    }

    /*
     * If the value at position j is smaller than the value 
     * at position i, they are swapped and the flip bit at 
     * position j is negated. In that case the method returns 
     * false. If no action was taken, the method returns true.
     */
    /*
    Join is the action above where we check against the left side and swap if we need to
    i is the ancestor, j is the child
    if i > j : swap(i,j)
             : flip(j)
             return false;
         return true;
     */
    boolean join (int i, int j) {
        int temp = elems.get(i).getValue();
        int temp2 = elems.get(j).getValue();
        if(temp > temp2) {
            swap(i,j);
            if (getFlip(j) == 0 ) {
                flips.set(j, 1);
            } else if (getFlip(j) == 1) {
                flips.set(j,0);
            }
            return false;
        }
        return true;
    }

    /*
     * The method starts by joining j with its distinguished ancestor. 
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp (int j) {
	// TODO
        try{
            int index = getDAncestorIndex(j);
            if(join(index, j)) { //the parent node of the last leaf calling the DAncestor, the last child
                return;
            } else {
                moveUp(index);
            }

        }catch(RootE e) {
//           throw new Error();
        }
    }

    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedely joins j with that leftmost child and its 
     * parents. 
     */
    void moveDown (int j) {
        // TODO
        try {
            int index = getLeftMostChildIndex();

            while (j != index) {
                join(j, index);
                index = getParentIndex(index);
            }

        } catch (RootE | NoLeftChildE e) {
       //     throw new Error();
        }
    }


    // Main methods: insert and extractMin

    /*
     * The method adds the new item at the end of the array making sure 
     * it calls setPosition and setHeap with the appropriate parameters 
     * and initializes the associated flip bit correctly. As a little 
     * optimization, if the inserted item is in a left child position, it 
     * will reset the flip bit of the parent to 0. 
     * The method then calls moveUp.
     */
    void insert (Item item) {
	// TODO
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
     //      throw new Error();
       }
    }

    /*
     * Like we did in the previous and as is outlined in the lecture notes, 
     * the last item in the array is moved to location 0. And then moveDown 
     * is called. Just make sure you don't call moveDown if the array has exactly 
     * one element!
     */
    Item extractMin () {
        swap(0, size - 1);
        Item one = elems.get(size - 1);
        elems.remove(size - 1);
        size--;
        if (size > 1) moveDown(0);
        return one;
    }

    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder () {
	// TODO
        try {
            for (int i= size-1; i >=1; i--) {
                int index = getDAncestorIndex(i);
                if (getValue(index) > getValue(i) ){ //if the value of i is less than the value of distinguished ancestor then return false
                    return false;
                }
            }
        } catch (RootE e) {
//            throw new Error();
        }
        return true;
    }

}
