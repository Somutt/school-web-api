package dev.samuel.school_web.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponseDTO(Integer status, String message, List<ErrorDescription> errors) {

    public static ErrorResponseDTO badRequest(String message) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }
}
