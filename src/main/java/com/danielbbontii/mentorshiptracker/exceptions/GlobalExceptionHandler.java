package com.danielbbontii.mentorshiptracker.exceptions;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String TITLE = "title";
    private static final String STATUS = "status";
    private static final String ERROR = "error";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        List<Map<String, Object>> violations = ex.getAllErrors()
                .stream()
                .map(violation -> {
                    Map<String, Object> violationDetails = new HashMap<>();
                    violationDetails.put(MESSAGE, violation.getDefaultMessage());
                    violationDetails.put(TITLE, HttpStatus.BAD_REQUEST);
                    violationDetails.put(STATUS, HttpStatus.BAD_REQUEST.value());
                    return violationDetails;
                }).toList();
        return new ResponseEntity<>(new ResponseDTO(ERROR, violations), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        Map<String, Object> error = new HashMap<>();

        error.put(MESSAGE, badCredentialsException.getMessage());
        error.put(TITLE, HttpStatus.FORBIDDEN.value());
        error.put(STATUS, HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(new ResponseDTO(ERROR, new Object[]{error}), HttpStatus.FORBIDDEN);
    }

    //org.springframework.security.access.AccessDeniedException
    //ExpiredJwtException
    //AuthenticationException
}
