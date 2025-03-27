package dev.samuel.school_web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleIllegalArgumentException(IllegalArgumentException e) {
        return ErrorResponseDTO.badRequest(e.getMessage());
    }

    //Request JSON lacking fields or out of validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorDescription> errors = fieldErrors
                                        .stream()
                                        .map(fe -> new ErrorDescription(fe.getField(), fe.getDefaultMessage()))
                                        .toList();

        return new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request validation failed", errors);
    }
}
