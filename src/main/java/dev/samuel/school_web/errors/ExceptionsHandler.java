package dev.samuel.school_web.errors;

import dev.samuel.school_web.errors.exceptions.AttachResourceNotFoundException;
import dev.samuel.school_web.errors.exceptions.DuplicatedRegisterException;
import dev.samuel.school_web.errors.exceptions.UnavailableResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
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

    //Body without JSON or unexpected format
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ErrorResponseDTO.badRequest("Body is missing or in unexpected format");
    }

    //Non-implemented methods response
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponseDTO handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ErrorResponseDTO.methodNotSupported("HTTP Method not supported for this request");
    }

    //Handles duplicates while registering entities that must be unique
    @ExceptionHandler(DuplicatedRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleDuplicatedRegisterException(DuplicatedRegisterException e) {
        return ErrorResponseDTO.conflict(e.getMessage());
    }

    //Handles bad formatted schedule while registering or updating classrooms
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleDateTimeParseException(DateTimeParseException e) {
        return ErrorResponseDTO.badRequest("Date format is invalid, please use dd/MM/yyyy HH:mm:ss format, example: 01/01/2027 22:50:00");
    }

    //Handles conflict of schedule for resource registration
    @ExceptionHandler(UnavailableResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleUnavailableResourceException(UnavailableResourceException e) {
        return ErrorResponseDTO.conflict(e.getMessage());
    }

    //Handles resource not found when attaching or detaching n:n tables
    @ExceptionHandler(AttachResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleAttachResourceNotFoundException(AttachResourceNotFoundException e) {
        return ErrorResponseDTO.notFound(e.getMessage());
    }

    /*
    //Generic RuntimeException handler
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleRuntimeException(RuntimeException e) {
        return ErrorResponseDTO.internalServerError("Unexpected error, send direct message or ticket");
    }
    */
}
