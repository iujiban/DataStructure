
import java.util.Random;
import java.util.function.Function;

// -------------------------------------------------------

abstract class HashFunction implements Function<Integer,Integer> {
    abstract int getBound ();
    abstract void setBound (int bound);
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with a parameter 'bound'. Then
 * every time the hash function is applied to an argument 'x', it
 * returns the value of 'x' modulo the 'bound'. See the test cases in
 * HashFunctionTest for some examples.
 *
 */
 class HashMod extends HashFunction {
    // complete the implementation and remove the abstract annotation
    int bound;
    public int getBound() {
        return this.bound;
    }
    public void setBound(int bound){

        this.bound = bound;

    }

    public HashMod(int bound){
        this.bound = bound;
    }

    public Integer apply(Integer x){
        int ans;
        while (x <0 ) {
            x += bound;
        }
        ans = x % this.bound;
        return ans;
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with two parameters: a first
 * argument 'bound' that is used to create an instance of the
 * superclass, and a second argument 'after' of type
 * Function<Integer,Integer> that is used as follows. When the hash
 * function is invoked, the basic functionality of the super class is
 * first invoked, and then that result is given to the function
 * 'after'. The result of 'after' is also wrapped around so that it does not exceed bound.
 * See the test cases in HashFunctionTest for some examples.
 *
 */
 class HashModThen extends HashMod {
    Function<Integer,Integer> after;
    public HashModThen(int bound, Function<Integer,Integer> after){
        super(bound);
        this.after = after;
    }
    public Integer apply(Integer x){
        int ans =super.apply(x);

        ans = after.apply(ans);

        return ans;
    }
    // complete the implementation and remove the abstract annotation
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with three parameters: a
 * random number generator 'r' of type Random and two integers 'p' and
 * 'm' where 'p' should be a prime number greater than or equal to
 * 'm'. Using the random number generator, the constructor generates
 * two random numbers 'a' and 'b' such that 'a' is in the range 1
 * (inclusive) and p (exclusive) and 'b' is in the range 0 (inclusive)
 * and p (exclusive). The resulting hash function should be a
 * universal hash function as explained in the book. Again see the
 * test cases in HashFunctionTest for some examples.
 *
 */
class HashUniversal extends HashFunction {
    Random r;
    int p;
    int m;
    int a;
    int b;


    // complete the implementation and remove the abstract annotation

    public HashUniversal(Random random, int prime1, int prime2){
        this.r = random;
        this.p = prime1;
        this.m = prime2;
        this.a = r.nextInt(this.p-1)+1;
        this.b = r.nextInt(this.p);
    }

    public Integer apply(Integer x){
        int ans = (((a*x)+b)%this.p)%this.m;
        return ans;
    }

    @Override
    int getBound() {
        return m;
    }

    @Override
    public void setBound(int bound) {
        this.m = m;
    }

    /*public static void main(String[] args){
        Random r = new Random(1);
        HashFunction one = new HashUniversal(r,31,10);
        System.out.println(one.apply(1));
    }
    */

}

// -------------------------------------------------------
// -------------------------------------------------------
