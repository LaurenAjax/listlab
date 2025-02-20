package edu.grinnell.csc207.listlab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

import java.util.Random;

public class ArrayListTests {
    
    /**
     * Tests that list is indeed empty when initialized
     * 
     */
    @Test
    public void emptyListTest() {
        ArrayList list = new ArrayList();
        assertEquals(0, list.size());
    }

    /**
     * Tests that the add adds to the list and that size increase as it does
     * 
     */
    @Test
    public void listAddSize10() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(10, list.size());
    }
    
    /**
     * Tests that add adds the correct values to the list, and that get 
     * retrieves the correct values from the list
     * 
     */
    @Test
    public void listAddGet21() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 21; i++) {
            list.add(i + 1);
        }
        assertEquals(1, list.get(0));
        assertEquals(10, list.get(9));
        assertEquals(21, list.get(20));
    }
    
    /**
     * Tests that remove removes values from the list appropriately and that 
     * what get retrieves changes as things are removed
     * 
     */
    @Test
    public void listRemoveGet16() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 16; i++) {
            list.add(i);
        }
        assertEquals(13, list.remove(13));
        assertEquals(15, list.get(14));
        assertEquals(3, list.remove(3));
        assertEquals(0, list.get(0));
        assertEquals(6, list.get(5));
        assertEquals(14, list.get(12));
        try {
            list.get(14);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        }
    }
    
    /**
     * Tests that clear empties the list and that isEmpty finds the list to be
     * empty when it is
     * 
     */
    @Test
    public void isListEmptyClear() {
        ArrayList list = new ArrayList();
        assertEquals(true, list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(false, list.isEmpty());
        list.clear();
        assertEquals(true, list.isEmpty());
        list.clear();
        assertEquals(true, list.isEmpty());
    }
    
    /**
     * Tests that the appropriate indexes are returned for various searched for
     * values
     * 
     */
    @Test
    public void indexOfList10() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(7, list.indexOf(7));
        assertEquals(0, list.indexOf(0));
        assertEquals(9, list.indexOf(9));
        assertEquals(-1, list.indexOf(10));
    }
    
    /**
     * Tests that contains successfully determines whether an element is in the 
     * list, even if it was in the list but was removed
     * 
     */
    @Test
    public void removeContains() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(true, list.contains(5));
        assertEquals(5, list.remove(5));
        assertEquals(false, list.contains(5));
        assertEquals(false, list.contains(15));
    }
    
    /**
     * Tests that insert succeeds in entering a value at an index in the list
     * when it is valid and catches thrown errors if not
     * 
     */
    @Test
    public void testInsert() {
        ArrayList list = new ArrayList();
        try {
            list.insert(14, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        }
        try {
            list.insert(-1, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        }
        list.insert(0, 15);
        assertEquals(15, list.get(0));
        for (int i = 1; i < 10; i++) {
            list.insert(i, 15 - i);
        }
        assertEquals(6, list.get(9));
        list.insert(4, 21);
        assertEquals(7, list.get(9));
        assertEquals(21, list.get(4));
    }
    
    /**
     * Tests that toString produces the correct string
     * 
     */
    @Test
    public void string1Through10() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());
    }
    
    /**
     * Tests that when two lists are identical, equals returns true, and false
     * otherwise
     * 
     */
    @Test
    public void equalArrayLists() {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        for (int i = 0; i < 5; i++) {
            list2.add(i);
        }
        assertEquals(false, list1.equals(list2));
        for (int i = 5; i < 10; i++) {
            list2.add(i);
        }
        assertEquals(true, list1.equals(list2));
        assertEquals(7, list2.remove(7));
        list2.insert(7, 20);
        assertEquals(false, list1.equals(list2));
    }
    
    /**
     * Tests that every element of a two concatenated lists is correct
     * 
     */
    @Test
    public void concatArrayLists() {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        for (int i = 0; i < 5; i++) {
            list2.add(i);
        }
        ArrayList list3 = list1.concat(list2);
        for (int i = 0; i < 10; i++) {
            assertEquals(i, list3.get(i));
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i, list3.get(i + 10));
        }
    }

    /**
     * Checks that the size returns correct for all list sizes
     * 
     * @param sz a randomly generated size of the list
     * @return whether or not the list size is correct
     */
    @Property
    public boolean listAddSize(@ForAll @IntRange(min = 0, max = 1000) int sz) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < sz; i++) {
            list.add(i);
        }
        return list.size() == sz;
    }
    
    /**
     * Checks whether a list of a randomly generated size returns the correct
     * value at a randomly generated index
     * 
     * @param sz a randomly generated size of the list
     * @return whether or not the element at k is correct
     */
    @Property
    public boolean listAddGet(@ForAll @IntRange(min = 1, max = 1000) int sz) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < sz; i++) {
            list.add(i);
        }
        int k = new Random().nextInt(sz);
        return list.get(k) == k;
    }
    
    /**
     * Checks whether or not a value is successfully inserted then removed
     * 
     * @param sz a randomly generated size of the list
     * @return whether or not the element was successfully inserted at k then
     * removed
     */
    @Property
    public boolean listRemoveInsert(@ForAll @IntRange(min = 1, max = 1000) int sz) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < sz; i++) {
            list.add(i);
        }
        int k = new Random().nextInt(sz);
        list.insert(k, -100);
        return list.remove(k) == -100;
    }
}
