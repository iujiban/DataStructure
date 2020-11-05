import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashTableTest {
    @Test
    public void hashSeparateChaining () {

        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r,31, 10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(1);
        ht.insert(12);
        assertTrue(ht.search(12));
        ht.delete(12);
        assertFalse(ht.search(12));
        assertTrue(ht.search(1));
        assertFalse(ht.search(2));
        ht.insert(22);
        System.out.println("Before rehashing");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehashing");
        System.out.println(ht);

    }
    @Test
    public void checkingHashTable() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(3);
        ht.insert(22);
        ht.insert(16);
        ht.insert(4);
        ht.delete(16);
        ht.insert(124);
        ht.insert(22);
        ht.delete(22);
        ht.insert(321);

        System.out.println(ht);
    }
    @Test
    public void fig55() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);

        HashFunction hf2 = new HashMod(10);
        HashTable ht2 = new HashSeparateChaining(hf2);
        ht2.insert(0);
        ht2.insert(15);
        ht2.insert(25);
        ht2.insert(24);
        ht2.insert(37);
        ht2.insert(49);
        ht2.insert(2);
        ht2.insert(14);
        ht2.insert(42);
        ht2.insert(86);
        System.out.println(ht2);
        // Checking delete method and search method
        assertTrue(ht2.search(15));
        assertTrue(ht2.search(25));
        assertTrue(ht2.search(86));
        assertTrue(ht2.search(42));
        ht2.delete(15);
        ht2.delete(25);
        ht2.delete(86);
        ht2.delete(42);
        assertFalse(ht2.search(15));
        assertFalse(ht2.search(25));
        assertFalse(ht2.search(86));
        assertFalse(ht2.search(42));
    }

    @Test
    public void fig511() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht);

        HashFunction hf2 = new HashMod(10);
        HashTable ht2 = new HashLinearProbing(hf2);
        ht2.insert(33);
        ht2.insert(13);
        ht2.insert(25);
        ht2.insert(34);
        ht2.insert(70);
        ht2.insert(24);
        ht2.insert(3);
        ht2.insert(15);
        ht2.insert(10);
        System.out.println(ht2);
        // Checking delete method and search method

        assertTrue(ht2.search(70));
        assertTrue(ht2.search(25));
        assertTrue(ht2.search(34));
        assertTrue(ht2.search(15));
        assertTrue(ht2.search(10));

        ht2.delete(70);
        ht2.delete(25);
        ht2.delete(34);
        ht2.delete(15);
        ht2.delete(10);
        assertFalse(ht2.search(70));
        assertFalse(ht2.search(25));
        assertFalse(ht2.search(34));
        assertFalse(ht2.search(15));
        assertFalse(ht2.search(10));
        // Check number of "X" in the slot. There should be 5, because I deleted five numbers.
        System.out.println(ht2);
        ht2.insert(10);
        ht2.insert(20);
        assertTrue(ht2.search(10));
        assertTrue(ht2.search(20));

        // Final check. First and second slot should be changed to 10 and 20.
        System.out.println(ht2);
    }
    @Test
    public void fig513() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing (hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.13");
        System.out.println(ht);
        ht.insert(26);
        ht.insert(72);
        ht.insert(95);
        System.out.println("Before rehash");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehash");
        System.out.println(ht);

        HashFunction hf2 = new HashMod(10);
        HashTable ht2 = new HashQuadProbing(hf2);
        ht2.insert(23);
        ht2.insert(15);
        ht2.insert(10);
        ht2.insert(48);
        ht2.insert(38);
        ht2.insert(28);
        ht2.insert(75);
        System.out.println(ht2);
        assertTrue(ht2.search(23));
        assertTrue(ht2.search(38));
        assertTrue(ht2.search(28));
        assertTrue(ht2.search(75));
        assertTrue(ht2.search(10));
        ht2.delete(23);
        ht2.delete(38);
        ht2.delete(28);
        ht2.delete(75);
        ht2.delete(10);
        assertFalse(ht2.search(23));
        assertFalse(ht2.search(38));
        assertFalse(ht2.search(28));
        assertFalse(ht2.search(75));
        assertFalse(ht2.search(10));
        System.out.println(ht2); //check numbers are correctly deleted
        ht2.insert(44);
        ht2.insert(24);
        assertTrue(ht2.search(44));
        assertTrue(ht2.search(24));
        System.out.println(ht2); // final check that 44 and 25 is correctly inserted in the table.
    }
    @Test
    public void fig518 () {
        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.18");
        System.out.println(ht);
    }

}