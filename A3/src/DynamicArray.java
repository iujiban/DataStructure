import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DynamicArray<E> implements StackI<E>, QueueI<E>, DequeI<E> {
    private Optional<E>[] elements;
    private int capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //

    @SuppressWarnings("unchecked")
    DynamicArray (int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    // Complete the definitions of the following methods from the interfaces

    @Override
    public void clear() {
        for (int i=0; i< size; i++)
            elements[i] = Optional.empty();
        size = 0;
    }

    public int size () {
        return size;
    }

    public void push(E item) {
        addFirst(item);
    }

    public E peek() throws NoSuchElementE {
        return getFirst();
    }

    public E pop() throws NoSuchElementE {
        return removeFirst();
    }

    public void offer(E elem) {
        addLast(elem);
    }

    public E poll() throws NoSuchElementE {
        return getFirst();
    }

    public E remove() throws NoSuchElementE {
        return removeFirst();
    }

    public void addFirst(E elem) {
        if (size == capacity) doubleCapacity();
        elements[front] = Optional.of(elem);
        front = Math.floorMod(front -1, capacity);
        size++;
    }

    public void addLast(E elem) {
        if (size == capacity) doubleCapacity();
        elements[back] = Optional.of(elem);
        back = Math.floorMod(back+1, capacity);
        size++;
    }

    public E getFirst() throws NoSuchElementE {
        if(size == 0) {
            throw new NoSuchElementE();
        }
        else {
            int First = Math.floorMod(front + 1, capacity);
            E getFirst = elements[First].get();
            return getFirst;
        }
    }

    public E getLast() throws NoSuchElementE {
        if(size ==0 ) {
            throw new NoSuchElementE();
        }
        else {
            int Last = Math.floorMod(back - 1, capacity);
            E getLast = elements[Last].get();
            return getLast;
        }
    }

    public E removeFirst() throws NoSuchElementE {
        E removefirst;
        int first = Math.floorMod(front+1, capacity);
        if (size > 0) {
            removefirst = elements[first].get();
            elements[first] = Optional.empty();
            size--;
        }
        else {
            throw new NoSuchElementE();
        }
        front = Math.floorMod(front+1,capacity);

        return removefirst;

    }

    public E removeLast() throws NoSuchElementE {
        E removeLast ;
        int Last = Math.floorMod(back-1,capacity);
        if (size > 0) {
            removeLast = elements[Last].get();
            elements[Last] = Optional.empty();
            size--;

        }
        else {
            throw new NoSuchElementE();
        }
        back = Math.floorMod(back-1,capacity);

        return removeLast;
    }

    // the following methods are used for debugging and testing;
    // please do not edit them

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

    void doubleCapacity() {
        //new elements
        Optional<E>[] temp;
        temp =(Optional<E>[]) Array.newInstance(Optional.class, capacity*2);
        //fill
        //Arrays.fill(temp, Optional.empty());
        //copy old elements to new elements
        //loop from i=0 to capacity -1

        for (int i=0; i < capacity ; i ++) {
            int n = Math.floorMod(front+1+i, capacity);
            temp[i] = elements[n];
        }
        //new elements[i] = old elements front +1 + i

        //capacity = 2 capacity
        capacity = 2 * capacity;
        //set back and front
        front = capacity -1;
        back  = size;
        elements = temp;
    }

    public static void main (String [] args) throws NoSuchElementE {
        DynamicArray<Integer> d = new DynamicArray<>(5);
        /*
        d.addFirst(3);
        d.addFirst(4);
        d.addFirst(5);
        System.out.println(d.getFirst());
        System.out.println(d.getLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        d.addFirst(3);
        System.out.println(d.getFirst());
        d.addFirst(4);
        d.addFirst(5);
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        */
        d.addFirst(8);
        d.addFirst(6);
        d.addFirst(4);
        d.addFirst(3);
        d.addFirst(2);
       // d.addFirst(1);

        System.out.println(d.getFirst());

        DynamicArray<Integer> d1 = new DynamicArray<>(5);
        d1.addFirst(1);
        d1.addFirst(2);
        d1.addFirst(3);
        System.out.println(d1.removeFirst());
        System.out.println(d1.removeFirst());
        System.out.println(d1.removeFirst());

        d1.addLast(1);
        d1.addLast(2);
        d1.addLast(3);
        System.out.println(d1.removeLast());
        System.out.println(d1.removeLast());
        System.out.println(d1.removeLast());
        // System.out.println(d.removeLast());
        // System.out.println(d.removeFirst());
        // System.out.println(d.removeFirst());
        // System.out.println(d.removeLast());
        // System.out.println(d.removeLast());
        /*
        DynamicArray<Integer> D1 = new DynamicArray<>(5);
        D1.push(1);
        D1.push(2);
        D1.push(6);
        System.out.println(D1.peek());
        */
    }
}

