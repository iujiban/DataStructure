import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
public class HeapTest {
    Item a = new Item("a", 100);
    Item b = new Item("b", 200);
    Item c = new Item("c", 300);
    Item d = new Item("d", 400);
    Item e = new Item("e", 500);

    ArrayList<Item> testingItem = new ArrayList<>(Arrays.asList(a,b,c,d,e));

    Item f = new Item("f", 50);
    Item g = new Item("g", 150);
    Item h = new Item("h", 250);
    Item j = new Item("j", 350);
    Item i = new Item("i", 450);


    @Test
    public void isEmpty() {
        BinaryHeap bh = new BinaryHeap();
        assertTrue(bh.isEmpty());

        Item one = new Item("one", 0);
        bh.insert(one);

        assertFalse(bh.isEmpty());
    }
    @Test
    public void sortBH () {
        ArrayList<Item> items = new ArrayList<>();
        Item it;
        //1, 2, 3, 4, 5, 6, 7, 8, 9
        it = new Item("a1",  1);
        items.add(it);

        it = new Item("a2",  2);
        it.reverse();
        items.add(it);

        it = new Item("a3",  3);
        items.add(it);

        it = new Item("a4",  4);
        it.reverse();
        items.add(it);

        it = new Item("a5",  5);
        items.add(it);

        it = new Item("a6",  6);
        items.add(it);

        it = new Item("a7",  7);
        items.add(it);

        it = new Item("a8",  8);
        items.add(it);

        it = new Item("a9",  9);
        items.add(it);

        BinaryHeap bhp = new BinaryHeap(items);
        System.out.println(bhp.getElems());
        TreePrinter.print(bhp.findMin());

       //inserting
        it = new Item("a10", 10);
        it.setHeap(bhp);
        bhp.insert(it);
        it = new Item("a11", 20);
        it.setHeap(bhp);
        bhp.insert(it);
        it = new Item("a12", 30);
        it.setHeap(bhp);
        bhp.insert(it);
        it = new Item("a13", 40);
        it.setHeap(bhp);
        bhp.insert(it);
        System.out.println(bhp.getElems());
        TreePrinter.print(bhp.findMin());

<<<<<<< HEAD
        //Another sorting
        ArrayList<Item> secondItem = new ArrayList<Item>();
        for (int i=0; i<testingItem.size();i++) {
            secondItem.add(testingItem.get(i));
        }
        BinaryHeap secondBHP = new BinaryHeap(secondItem);
        System.out.println(secondBHP.getElems());
        TreePrinter.print(secondBHP.findMin());

        //Inserting sorting
        f.setHeap(secondBHP);
        secondBHP.insert(f);

        g.setHeap(secondBHP);
        secondBHP.insert(g);

        h.setHeap(secondBHP);
        secondBHP.insert(h);

        j.setHeap(secondBHP);
        secondBHP.insert(j);

        i.setHeap(secondBHP);
        secondBHP.insert(i);

        System.out.println(secondBHP.getElems());
        TreePrinter.print(secondBHP.findMin());
      //  TreePrinter.print(secondBHP.extractMin());

      for(int i=1; i< 10; i++) assertEquals(i, bhp.extractMin().getValue());
      assertEquals(10, bhp.extractMin().getValue());
      assertEquals(20, bhp.extractMin().getValue());
      assertEquals(30, bhp.extractMin().getValue());
      assertEquals(40, bhp.extractMin().getValue());
=======
        for (int i = 1; i < 10; i++) assertEquals(i, bhp.extractMin().getValue());

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

}
