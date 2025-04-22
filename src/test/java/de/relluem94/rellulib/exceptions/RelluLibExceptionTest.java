package de.relluem94.rellulib.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelluLibExceptionTest {
    @Test
    public void testRelluLibExceptionConstructorWithMessage() {
        String errorMessage = "Test RelluLib exception message";
        RelluLibException exception = new RelluLibException(errorMessage);

        assertNotNull(exception, "Exception should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the provided message");
    }

    @Test
    public void testRelluLibExceptionIsInstanceOfException() {
        RelluLibException exception = new RelluLibException("Test message");
        assertInstanceOf(Exception.class, exception, "RelluLibException should be an instance of Exception");
    }
}