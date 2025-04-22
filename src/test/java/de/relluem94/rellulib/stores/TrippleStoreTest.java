package de.relluem94.rellulib.stores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class TrippleStoreTest {
    TrippleStore<String, Integer, Boolean> trippleStore;
    TrippleStore<?, ?, ?> trippleStoreRandom;

    @BeforeEach
    void setUp(){
        trippleStore = new TrippleStore<>("Test", 1994, true);
        trippleStoreRandom = new TrippleStore<>("Test", 1994, true);
    }

    @Test
    void setThirdValue() {
        Assertions.assertInstanceOf(Boolean.class, trippleStore.getThirdValue());
        Assertions.assertTrue(trippleStore.getThirdValue());

        trippleStore.setThirdValue(false);

        Assertions.assertInstanceOf(Boolean.class, trippleStore.getThirdValue());
        Assertions.assertFalse(trippleStore.getThirdValue());
    }

    @Test
    void getThirdValueRandom() {
        Assertions.assertInstanceOf(Boolean.class, trippleStoreRandom.getThirdValue());
        Assertions.assertTrue((Boolean) trippleStoreRandom.getThirdValue());
    }

    @Test
    void testToString() {
        TrippleStore<String, Integer, Double> store = new TrippleStore<>("test", 42, 3.14);
        String expected = "First Value: test\nSecond Value: 42\nThird Value: 3.14";
        Assertions.assertEquals(expected, store.toString(), "toString should return correct string representation");
    }

    @Test
    void testHashCode() {
        TrippleStore<String, Integer, Double> store1 = new TrippleStore<>("test", 42, 3.14);
        TrippleStore<String, Integer, Double> store2 = new TrippleStore<>("test", 42, 3.14);
        Assertions.assertEquals(store1.hashCode(), store2.hashCode(), "Objects with same values should have same hash code");
        int expectedHash = 94 * Objects.hashCode("test") * Objects.hashCode(42) * Objects.hashCode(3.14);
        Assertions.assertEquals(expectedHash, store1.hashCode(), "Hash code should be 94 * value.hashCode * secondValue.hashCode * thirdValue.hashCode");

        TrippleStore<String, Integer, Double> different = new TrippleStore<>("other", 99, 1.0);
        Assertions.assertNotEquals(store1.hashCode(), different.hashCode(), "Objects with different values should have different hash codes");

        TrippleStore<String, Integer, Double> nullValues = new TrippleStore<>(null, null, null);
        Assertions.assertEquals(0, nullValues.hashCode(), "Hash code for null values should be 0");
    }

    @SuppressWarnings("all")
    @Test
    void testEquals() {
        TrippleStore<String, Integer, Double> store = new TrippleStore<>("test", 42, 3.14);
        TrippleStore<String, Integer, Double> sameValues = new TrippleStore<>("test", 42, 3.14);
        TrippleStore<String, Integer, Double> differentFirst = new TrippleStore<>("other", 42, 3.14);
        TrippleStore<String, Integer, Double> differentSecond = new TrippleStore<>("test", 99, 3.14);
        TrippleStore<String, Integer, Double> differentThird = new TrippleStore<>("test", 42, 1.0);

        Assertions.assertEquals(store, store, "Same object should be equal");
        Assertions.assertNotEquals(store, null, "Comparison with null should be false");
        Assertions.assertNotEquals(store, new Object(), "Different class should be unequal");
        Assertions.assertEquals(store, sameValues, "Objects with same values should be equal");
        Assertions.assertNotEquals(store, differentFirst, "Objects with different first value should be unequal");
        Assertions.assertNotEquals(store, differentSecond, "Objects with different second value should be unequal");
        Assertions.assertNotEquals(store, differentThird, "Objects with different third value should be unequal");

        TrippleStore<String, Integer, Double> nullValues1 = new TrippleStore<>(null, null, null);
        TrippleStore<String, Integer, Double> nullValues2 = new TrippleStore<>(null, null, null);
        Assertions.assertEquals(nullValues1, nullValues2, "Objects with null values should be equal");

        TrippleStore<String, Integer, Double> oneNullValue = new TrippleStore<>("test", 42, null);
        TrippleStore<String, Integer, Double> nonNullValue = new TrippleStore<>("test", 42, 3.14);
        Assertions.assertNotEquals(oneNullValue, nonNullValue, "Objects with null and non-null third value should be unequal");
    }
}