package dev.samuel.school_web.errors.exceptions;

import java.io.Serial;

public class AttachResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3078738666127298093L;

    public AttachResourceNotFoundException(String message) {
        super(message);
    }
}
