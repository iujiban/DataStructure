import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AVLTest {
    BST bst;
    AVL avl;

    @Before
    public void setUp() {
        List<Integer> nums = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        Collections.shuffle(nums);
        bst = BST.EBST;
        avl = AVL.EAVL;
        for (int i : nums) {
            bst = bst.BSTinsert(i);
            avl = avl.AVLinsert(i);
        }
    }

    @Test
    public void toAVL() {
        BST bst2 = AVL.toBST(BST.toAVL(bst));
        Iterator<Integer> bstIter = bst.iterator();
        Iterator<Integer> bst2Iter = bst2.iterator();
        while (bstIter.hasNext() && bst2Iter.hasNext()) {
            assertEquals((int)bstIter.next(),(int)bst2Iter.next());
        }
    }

    @Test
    public void AVLeasyRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(15);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(20, avl.AVLData());
        assertEquals(10,left.AVLData());
        assertEquals(15, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(30, right.AVLLeft().AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }
    @Test
    public void AVLeasyRight2() throws EmptyAVLE{
        AVL t = new AVLNode(40,new AVLNode(20, new AVLNode(10, new EmptyAVL(),AVL.AVLLeaf(15)),AVL.AVLLeaf(30)),AVL.AVLLeaf(50));
        AVL t2 = new AVLNode(20, new AVLNode(10, new EmptyAVL(), AVL.AVLLeaf(15)),new AVLNode(40, AVL.AVLLeaf(30),AVL.AVLLeaf(50)));
        AVL t3 = new AVLNode(20, new AVLNode(10, AVL.AVLLeaf(15),new EmptyAVL()),new EmptyAVL());
        AVL t4 = new AVLNode(10, AVLNode.AVLLeaf(15),AVL.AVLLeaf(20));
        AVL t5 = new AVLNode(20, new AVLNode(10, AVL.AVLLeaf(15),AVL.AVLLeaf(18)),new EmptyAVL());
        AVL t6 = new AVLNode(10, AVL.AVLLeaf(15),new AVLNode(20, AVL.AVLLeaf(18),new EmptyAVL()));

        assertEquals(t2,t.AVLeasyRight());
        assertEquals(t4,t3.AVLeasyRight());
        assertEquals(t6,t5.AVLeasyRight());
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(25);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(30, avl.AVLData());
        assertEquals(20,left.AVLData());
        assertEquals(10, left.AVLLeft().AVLData());
        assertEquals(25, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }
    @Test
    public void AVLRotateRight2(){
        AVL t = new AVLNode(40, new AVLNode(20, AVL.AVLLeaf(10),new AVLNode(30, AVL.AVLLeaf(25),new EmptyAVL())),AVL.AVLLeaf(50));
        AVL t2 = new AVLNode(30, new AVLNode(20, AVL.AVLLeaf(10), AVL.AVLLeaf(25)), new AVLNode(40,new EmptyAVL(),AVL.AVLLeaf(50)));
        AVL t3 = new AVLNode(40, new AVLNode(20, AVL.AVLLeaf(10),new AVLNode(30, AVL.AVLLeaf(25),new AVLNode(35,new EmptyAVL(), AVL.AVLLeaf(37)))), AVL.AVLLeaf(50));
        AVL t4 = new AVLNode(30, new AVLNode(20, AVL.AVLLeaf(10), AVL.AVLLeaf(25)),new AVLNode(40, new AVLNode(35,new EmptyAVL(), AVL.AVLLeaf(37)), AVL.AVLLeaf(50)));
        AVL t5 = new AVLNode(40, new AVLNode(20, AVL.AVLLeaf(10),new AVLNode(34, new EmptyAVL(), AVL.AVLLeaf(37))),new EmptyAVL());
        AVL t6 = new AVLNode(34, new AVLNode(20, AVL.AVLLeaf(10),new EmptyAVL()),new AVLNode(40, AVL.AVLLeaf(37),new EmptyAVL()));


        assertEquals(t2,t.AVLrotateRight());
        assertEquals(t4,t3.AVLrotateRight());
        assertEquals(t6,t5.AVLrotateRight());

    }
    @Test
    public void AVLeasyLeft() {
        AVL tree = new AVLNode(40, AVL.AVLLeaf(20), new AVLNode(50, AVL.AVLLeaf(45), new AVLNode(65, new EmptyAVL(), AVL.AVLLeaf(75))));
        AVL tree2 = new AVLNode(50, new AVLNode(40, AVL.AVLLeaf(20),AVL.AVLLeaf(45)), new AVLNode(65, new EmptyAVL(), AVL.AVLLeaf(75)));
        AVL tree3 = new AVLNode(40, new EmptyAVL(), new AVLNode(50, AVL.AVLLeaf(48), new EmptyAVL()));
        AVL tree4 = new AVLNode(50, new AVLNode(40 , new EmptyAVL(), AVL.AVLLeaf(48)) , new EmptyAVL());

        assertEquals(tree2, tree.AVLeasyLeft());
        assertEquals(tree4, tree3.AVLeasyLeft());

    }
    @Test
    public void AVLRotateLeft() {
        AVL tree = new AVLNode(40, AVL.AVLLeaf(20), new AVLNode(50, new AVLNode(46, AVL.AVLLeaf(44), new EmptyAVL()), AVL.AVLLeaf(52)));
        AVL tree2 = new AVLNode(46, new AVLNode(40, AVL.AVLLeaf(20), AVL.AVLLeaf(44)), new AVLNode(50, new EmptyAVL(), AVL.AVLLeaf(52)));
        AVL tree3 = new AVLNode(40, new EmptyAVL(), new AVLNode(50, new AVLNode(46, AVL.AVLLeaf(42), new EmptyAVL()), AVL.AVLLeaf(60)));
        AVL tree4 = new AVLNode(46, new AVLNode(40, new EmptyAVL(), AVL.AVLLeaf(42)), new AVLNode(50, new EmptyAVL(), AVL.AVLLeaf(60)));

        assertEquals(tree2, tree.AVLrotateLeft());;
        assertEquals(tree4, tree3.AVLrotateLeft());
    }

    @Test
    public void AVLdelete() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(35);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(7);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(5);
        avl = avl.AVLinsert(10);

        AVL avl2 = avl.AVLdelete(35);
        AVL left = avl2.AVLLeft();
        AVL right = avl2.AVLRight();
        assertEquals(30, avl2.AVLData());
        assertEquals(7,left.AVLData());
        assertEquals(5, left.AVLLeft().AVLData());
        assertEquals(20, left.AVLRight().AVLData());
        assertEquals(10, left.AVLRight().AVLLeft().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());




        int n = 50;
        int l = -20;
        int g = 20;


        avl = new EmptyAVL();
        List<Integer> nums = new ArrayList<>();
        List<Integer> numc = new LinkedList<>();

        HashSet<Integer> seen = new HashSet<Integer>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int r = l + rand.nextInt(g - l);
            if (!seen.contains(r)){
                nums.add(r);
                numc.add(r);
                seen.add(r);
            }
        }
        n = seen.size();
        for (int i = 0; i < n; i++) {

            avl = avl.AVLinsert(nums.get(i));
        }

        try {
            avl.AVLdelete(10000000);
            fail("AVL doesn't throw an exception when a number that is not in the AVL is deleted.");
        } catch (EmptyAVLE e) {
        }
    }
}

