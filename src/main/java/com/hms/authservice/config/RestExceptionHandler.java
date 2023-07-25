package com.hms.authservice.config;

import com.hms.authservice.exception.InvalidRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidRequestException.class })
    protected final ResponseEntity<Object> handleInvalidRequest(
            final RuntimeException ex, final WebRequest request) {
        String bodyOfResponse = "Invalid request";
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {BadCredentialsException.class })
    protected final ResponseEntity<Object> badCredentialException(
            final RuntimeException ex, final WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED,
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        String message = new String();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message = error.getDefaultMessage();
        }
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST,message);
        return this.handleExceptionInternal(ex, apiResponse, headers, apiResponse.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage());
        return this.handleExceptionInternal(ex, apiResponse, headers, status, request);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    protected final ResponseEntity<Object> dataIntegrityViolationException(
            final RuntimeException ex, final WebRequest request) {
        String bodyOfResponse = "Duplicate key";
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.CONFLICT, bodyOfResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    protected final ResponseEntity<Object> handleMethodArgumentTypeException(
            final RuntimeException ex, final WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.CONFLICT, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return this.handleExceptionInternal(ex, apiResponse, headers, apiResponse.getStatus(), request);
    }

}
