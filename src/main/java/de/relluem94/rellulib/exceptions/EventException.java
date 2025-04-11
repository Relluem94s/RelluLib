package de.relluem94.rellulib.exceptions;

import java.io.Serial;

public class EventException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public EventException(String message) {
        super(message);
    }
}
