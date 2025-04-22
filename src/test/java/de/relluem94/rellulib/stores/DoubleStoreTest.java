package de.relluem94.rellulib.stores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DoubleStoreTest {

    DoubleStore<String, Integer> doubleStore;

    @BeforeEach
    void setUp(){
        doubleStore = new DoubleStore<>("Test", 1994);
    }

    @Test
    void setValue() {
        Assertions.assertEquals("Test", doubleStore.getValue());
        doubleStore.setValue("noTest");
        Assertions.assertNotEquals("Test", doubleStore.getValue());
        Assertions.assertEquals("noTest", doubleStore.getValue());
    }

    @Test
    void setSecondValue() {
        Assertions.assertEquals(1994, doubleStore.getSecondValue());
        doubleStore.setSecondValue(1998);
        Assertions.assertNotEquals(1994, doubleStore.getSecondValue());
        Assertions.assertEquals(1998, doubleStore.getSecondValue());
    }

    @Test
    void testValueIsEmpty(){
        DoubleStore<String, Integer> invalideDoubleStore = new DoubleStore<>(null, null);

        Assertions.assertNull(invalideDoubleStore.getValue());
        Assertions.assertNull(invalideDoubleStore.getSecondValue());
    }

    @Test
    void testHashCode(){
        Assertions.assertEquals(-1031710968, doubleStore.hashCode());
    }

    @SuppressWarnings("all")
    @Test
    void testEquals(){
        DoubleStore<String, Integer> doubleStore = new DoubleStore<>("test", 42);
        DoubleStore<String, Integer> sameValues = new DoubleStore<>("test", 42);
        DoubleStore<String, Integer> differentSecondValue = new DoubleStore<>("test", -42);
        DoubleStore<String, Integer> differentFirstValue = new DoubleStore<>("other", 42);
        DoubleStore<String, Integer> bothDifferent = new DoubleStore<>("other", -42);

        Assertions.assertEquals(doubleStore, doubleStore, "Same object should be equal");
        Assertions.assertNotEquals(doubleStore, null, "Comparison with null should be false");
        Assertions.assertNotEquals(doubleStore, new Object(), "Different class should be unequal");
        Assertions.assertEquals(doubleStore, sameValues, "Objects with same values should be equal");
        Assertions.assertNotEquals(doubleStore, differentSecondValue, "Objects with different second value should be unequal");
        Assertions.assertNotEquals(doubleStore, differentFirstValue, "Objects with different first value should be unequal");
        Assertions.assertNotEquals(doubleStore, bothDifferent, "Objects with both different values should be unequal");

        DoubleStore<String, Integer> nullValues = new DoubleStore<>(null, null);
        DoubleStore<String, Integer> otherNullValues = new DoubleStore<>(null, null);
        Assertions.assertEquals(nullValues, otherNullValues, "Objects with null values should be equal");

        DoubleStore<String, Integer> oneNullValue = new DoubleStore<>("test", null);
        DoubleStore<String, Integer> differentNullValue = new DoubleStore<>("test", 42);
        Assertions.assertNotEquals(oneNullValue, differentNullValue, "Objects with null and non-null second value should be unequal");
    }
}