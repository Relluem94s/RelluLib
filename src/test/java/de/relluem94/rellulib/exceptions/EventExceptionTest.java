package de.relluem94.rellulib.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventExceptionTest {

    @Test
    public void testEventExceptionConstructorWithMessage() {
        String errorMessage = "Test event exception message";
        EventException exception = new EventException(errorMessage);

        assertNotNull(exception, "Exception should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the provided message");
    }

    @Test
    public void testEventExceptionIsInstanceOfException() {
        EventException exception = new EventException("Test message");
        assertInstanceOf(Exception.class, exception, "EventException should be an instance of Exception");
    }
}