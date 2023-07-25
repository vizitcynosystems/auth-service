package com.hms.authservice.config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private UUID id;
    private String message;
    private Object data;
    private HttpStatus status;
    private List<String> errors;

    public ApiResponse(final String message , final UUID id) {
        this.id = id;
        this.message = message;
    }
    public ApiResponse(final String message) {
        this.message = message;
    }

    public ApiResponse(final HttpStatus status,
                       final String message, final List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    public static ApiResponse success(final String message ) {
        return new ApiResponse(message);
    }

    public static ApiResponse successMethod(final String message , final UUID id) {
        return new ApiResponse(message,id);
    }

    public ApiResponse(final HttpStatus status, final String message) {
        this.message = message;
        this.status = status;
    }
}
