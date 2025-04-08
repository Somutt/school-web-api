package dev.samuel.school_web.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponseDTO(Integer status, String message, List<ErrorDescription> errors) {

    public static ErrorResponseDTO badRequest(String message) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponseDTO methodNotSupported(String message) {
        return new ErrorResponseDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), message, List.of());
    }

    public static ErrorResponseDTO internalServerError(String message) {
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, List.of());
    }

    public static ErrorResponseDTO conflict(String message) {
        return new ErrorResponseDTO(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
