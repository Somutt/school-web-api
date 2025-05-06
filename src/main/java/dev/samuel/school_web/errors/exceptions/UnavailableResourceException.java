package dev.samuel.school_web.errors.exceptions;

import java.io.Serial;

public class UnavailableResourceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -412435862733022906L;

    public UnavailableResourceException(String message) {
        super(message);
    }
}
