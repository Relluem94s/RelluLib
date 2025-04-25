package de.relluem94.rellulib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedSizeListTest {
    @Test
    void testConstructorWithCapacity() {
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        assertEquals(5, list.size(), "List should have a size of 5");

        for (int i = 0; i < 5; i++) {
            assertNull(list.get(i), "Element at index " + i + " should be null");
            list.set(i, 1994);
            assertNotNull(list.get(i));
        }
    }

    @Test
    void testConstructorWithArray() {
        Integer[] initialElements = {1, 2, 3};
        FixedSizeList<Integer> list = new FixedSizeList<>(initialElements);

        assertEquals(3, list.size(), "List should have a size of 3");

        for (int i = 0; i < 3; i++) {
            assertEquals(initialElements[i], list.get(i), "Element at index " + i + " should be " + initialElements[i]);
        }
    }

    @Test
    void testClearThrowsException() {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        // Check that calling clear() throws an UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, list::clear, "clear() should throw an UnsupportedOperationException");
    }

    @Test
    void testAddThrowsException() {
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        assertThrows(UnsupportedOperationException.class, () -> list.add(10), "add() should throw an UnsupportedOperationException");
        assertEquals(5, list.size(), "Size should remain unchanged after clear() call.");
    }

    @Test
    void testAddAtIndexThrowsException() {
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        // Check that calling add(index, element) throws an UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> list.add(1, 10), "add(index, element) should throw an UnsupportedOperationException");
        assertEquals(5, list.size(), "Size should remain unchanged after clear() call.");
    }

    @Test
    void testRemoveAtIndexThrowsException() {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        assertThrows(UnsupportedOperationException.class, () -> list.remove(0), "remove(index) should throw an UnsupportedOperationException");
        assertEquals(5, list.size(), "Size should remain unchanged after clear() call.");
    }

    @Test
    void testRemoveObjectThrowsException() {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        assertThrows(UnsupportedOperationException.class, () -> list.remove((Integer) 1), "remove(Object) should throw an UnsupportedOperationException");
        assertEquals(5, list.size(), "Size should remain unchanged after clear() call.");
    }

    @Test
    void testRemoveRangeThrowsException() {
        FixedSizeList<Integer> list = new FixedSizeList<>(5);

        assertThrows(UnsupportedOperationException.class, () -> list.removeRange(0, 1), "removeRange() should throw an UnsupportedOperationException");
        assertEquals(5, list.size(), "Size should remain unchanged after clear() call.");
    }
}