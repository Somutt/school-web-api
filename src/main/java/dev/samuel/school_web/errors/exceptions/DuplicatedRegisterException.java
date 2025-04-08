package dev.samuel.school_web.errors.exceptions;

import java.io.Serial;

public class DuplicatedRegisterException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3318549778790007770L;

    public DuplicatedRegisterException(String message) {
        super(message);
    }
}
