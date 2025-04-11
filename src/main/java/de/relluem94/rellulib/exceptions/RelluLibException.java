package de.relluem94.rellulib.exceptions;

import java.io.Serial;

@Deprecated
public class RelluLibException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public RelluLibException(String message) {
        super(message);
    }
}
