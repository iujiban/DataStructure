

class EmptyListE extends Exception {}

abstract class List<E> {
    //ADT
    abstract boolean isEmpty ();
    abstract boolean isSingleton();
    abstract E getFirst() throws EmptyListE;
    abstract List<E> getRest() throws EmptyListE;
    abstract E get (int index) throws EmptyListE;
    abstract int length ();
    abstract List<E> append (List<E> other);
    abstract boolean contains (E elem);
}

    class EmptyL<E> extends List<E> {
    boolean isEmpty () {return true;}
    boolean isSingleton() {return false;}
    E getFirst() throws EmptyListE {throw new EmptyListE();}
    List <E> getRest() throws EmptyListE {throw new EmptyListE();}
    E get (int index) throws EmptyListE{throw new EmptyListE();}
    int length() {return 0;}
    List<E> append(List<E> other) {return other;}
    boolean contains (E elem) { return false;}


    
}

    class NodeL<E> extends List<E> {
    // NodeL<E> class with data and rest fields
    private E data;
    private List<E> rest;

    NodeL(E data, List<E> rest) {
        //To initialize these fields to the called constructor's arguments.
        this.data = data;
        this.rest = rest;
    }

    // create the method isEmpty() : true if the length of the String is 0
    boolean isEmpty() {
        return false;
    }
    // create the method isSingleton():  true if the length of the String is 1
    //  only exist one single Node
    boolean isSingleton() {
        return rest.isEmpty();
    }
    // create the method getFirst(): return the first element data
    E getFirst () {
        return data;
    }
    // create the method getRest(): return the rest of the data
    List<E> getRest() {
        return rest;
    }
    // create the method get() : return the data from the index involved in
    E get (int index) throws EmptyListE {
        if(index ==0) return data;
        else return rest.get(index-1);
    }
    //create the method length() : return the length of the data
    int length() {
        return 1 + rest.length();
    }
    // create the method append(List<E> other) : append each of the data
    List<E> append(List<E> other) {
       return new NodeL(data, rest.append(other));
    }
    // create the method contains(E elem) : return true if it contains the data
    boolean contains(E elem) {
        return data.equals(elem) || rest.contains(elem);
    }

} // to complete
