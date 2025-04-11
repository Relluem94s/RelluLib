package de.relluem94.rellulib.stores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrippleStoreTest {
    TrippleStore trippleStore;

    @BeforeEach
    void setUp(){
        trippleStore = new TrippleStore("Test", 1994, true);
    }

    @Test
    void setThirdValue() {
        Assertions.assertInstanceOf(Boolean.class, trippleStore.getThirdValue());
        Assertions.assertTrue((Boolean) trippleStore.getThirdValue());

        trippleStore.setThirdValue(false);

        Assertions.assertInstanceOf(Boolean.class, trippleStore.getThirdValue());
        Assertions.assertFalse((Boolean) trippleStore.getThirdValue());
    }
}