package de.relluem94.rellulib.stores;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    @Test
    void testToString() {
        Store<String> store = new Store<>("test");
        assertEquals("Value: test", store.toString(), "toString should return correct string representation");
    }

    @Test
    void testHashCode() {
        Store<String> store1 = new Store<>("test");
        Store<String> store2 = new Store<>("test");
        assertEquals(store1.hashCode(), store2.hashCode(), "Objects with same value should have same hash code");
        assertEquals(69 * "test".hashCode(), store1.hashCode(), "Hash code should be 69 * value.hashCode");

        Store<String> different = new Store<>("other");
        assertNotEquals(store1.hashCode(), different.hashCode(), "Objects with different values should have different hash codes");

        Store<String> nullValue = new Store<>(null);
        assertEquals(69 * Objects.hashCode(null), nullValue.hashCode(), "Hash code for null value should be correct");
    }

    @SuppressWarnings("all")
    @Test
    void testEquals() {
        Store<String> store = new Store<>("test");
        Store<String> sameValue = new Store<>("test");
        Store<String> differentValue = new Store<>("other");

        assertEquals(store, store, "Same object should be equal");
        assertNotEquals(store, null, "Comparison with null should be false");
        assertNotEquals(store, new Object(), "Different class should be unequal");
        assertEquals(store, sameValue, "Objects with same value should be equal");
        assertNotEquals(store, differentValue, "Objects with different value should be unequal");

        Store<String> nullValue1 = new Store<>(null);
        Store<String> nullValue2 = new Store<>(null);
        assertEquals(nullValue1, nullValue2, "Objects with null values should be equal");

        Store<String> oneNullValue = new Store<>(null);
        Store<String> nonNullValue = new Store<>("test");
        assertNotEquals(oneNullValue, nonNullValue, "Objects with null and non-null value should be unequal");
    }
}