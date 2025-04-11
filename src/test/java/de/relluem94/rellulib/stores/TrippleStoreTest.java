package de.relluem94.rellulib.stores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}