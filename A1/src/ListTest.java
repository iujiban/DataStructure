import org.junit.Test;
import static org.junit.Assert.*;
public class ListTest {

    @Test
    public void isEmpty() {
        List<String> s = new EmptyL<>();
        List<Integer> i = new NodeL<>(3,new EmptyL<>());

        assertEquals(true,s.isEmpty());
        assertEquals(false,i.isEmpty());
    }

    @Test
    public void isSingleton() {
    List<String> s = new EmptyL<>();
    List<Integer> i = new NodeL<>(1, new NodeL<>(2, new EmptyL<>()));
    List<String> s1 = new NodeL<>("banji", new EmptyL<>());

    assertEquals(false, s.isSingleton());
    assertEquals(false, i.isSingleton());
    assertEquals(true, s1.isSingleton());
    }


    @Test
    public void getFirst() throws EmptyListE {
        List<String> s = new NodeL<>("abc", new NodeL<>("defgh", new EmptyL<>()));
        List<String> s1 = new NodeL<>("fff", new NodeL<> ("fffff", new EmptyL<>()));
        List<Integer> i = new NodeL<>(1, new NodeL<>(3, new EmptyL<>()));

        assertEquals("abc", s.getFirst());
        assertEquals("fff", s1.getFirst());
        assertEquals((Integer)1, i.getFirst());
    }

    @Test (expected = EmptyListE.class)
    public void getRest() throws Exception {
        List<Integer> one = new NodeL<>(3, new EmptyL<>());
        List<Integer> empty = new EmptyL<>();
        List<String> stringOne = new NodeL<>("abc", new EmptyL<>());
        List<String > emptyString = new EmptyL<>();

        List<Integer> two = new NodeL<>(2,new NodeL<>(3, new EmptyL<>()));
        List<String> stringTwo = new NodeL<>("bbb", new NodeL<>("abc", new EmptyL<>()));

        assertEquals(one, empty.getRest());
        assertEquals(stringOne, emptyString.getRest());

        assertEquals(one, two.getRest());
        assertEquals(stringOne, stringTwo.getRest());

    }
    @Test (expected = EmptyListE.class)
    public void get() throws Exception {
        List<Integer> empty = new EmptyL<>();
        List<Integer> one = new NodeL<>(2, new EmptyL<>());
        List<Integer> three = new NodeL<>(3, new NodeL<>(3, new NodeL<>(3, new EmptyL<>())));

        int emptyIndex = empty.length(); // 0
        int index = one.length(); // 1
        int indexThree = three.length(); // 3

       assertEquals((Integer) 0, empty.get(emptyIndex));
       assertEquals((Integer) 2,one.get(index));
       assertEquals((Integer) 3, three.get(indexThree));

    }
    @Test
    public void length() {
        List<String> s = new NodeL<>("abc", new NodeL("banji", new EmptyL()));
        List<Integer> s1 = new NodeL<>(1, new NodeL<>(2, new NodeL<>(1, new EmptyL<>())));
        List<Integer> s2 = new EmptyL<>();

        assertEquals(2,s.length());
        assertEquals(3, s1.length());
        assertEquals(0,s2.length());
    }
    @Test
    public void append() {
        List<Integer> empty = new EmptyL<>();
        List<Integer> one = new NodeL<>(3, new EmptyL<>());
        List<Integer> otherOne = new NodeL<>(3, new EmptyL<>());
        List<Integer> two = new NodeL<>(3, new NodeL<>(3, new EmptyL<>()));
        List<Integer> oneThree = new NodeL<>(3, new EmptyL<>());
        List<Integer> three= new NodeL<>(1, new NodeL<>(2, new NodeL<>(3, new EmptyL<>())));


        assertEquals(one, empty.append(one));
        assertEquals(two, one.append(one));
        assertEquals(three, two.append(one));

    }

    @Test
    public void contains() {
        List<String> s = new NodeL<>("abc", new NodeL<>("d", new NodeL<>("jiban", new EmptyL<>())));
        List<Integer> i = new NodeL<>(1, new EmptyL<>());

        assertEquals(true, s.contains("jiban"));
        assertEquals(false, s.contains("ahri"));
        assertEquals(true, i.contains(1));
        assertEquals(false, i.contains(0));

    }


}
