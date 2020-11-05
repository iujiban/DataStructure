import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DequeListTest {
    DequeList<Integer>  empty, one, three, many;
    private Random rand;
    private int len;

    @Before
    public void setUp() throws  Exception {
        rand = new Random(1);
        empty = new EmptyL<>();
        one = new NodeL<>(5, empty);
        three = new NodeL<>(10, new NodeL<>(20, new NodeL<>(30, empty)));
        len = 4999;
        many = new EmptyL<>();
        for (int i=0; i<len; i++) {
            many = new NodeL<>(rand.nextInt(), many);
        }
    }

    @After
    public void tearDown() throws Exception {
        rand = null;
        empty = null;
        one = null;
        three = null;
        len = 0;
        many = null;
    }

    @Test
    public void clear() {
    }

    @Test
    public void addFirst() {
    }

    @Test
    public void addLast() {
    }

    @Test
    public void getFirst() {
    }

    @Test
    public void getLast() {
    }

    @Test
    public void size() {
    }
}