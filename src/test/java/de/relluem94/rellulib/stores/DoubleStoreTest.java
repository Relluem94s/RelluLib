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
}