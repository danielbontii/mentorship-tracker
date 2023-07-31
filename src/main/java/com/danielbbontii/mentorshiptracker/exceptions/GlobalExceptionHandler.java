package com.danielbbontii.mentorshiptracker.exceptions;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String TITLE = "title";
    private static final String STATUS = "status";
    private static final String ERROR = "error";

    private static final Map<String, Object> ERROR_DETAILS = new HashMap<>();


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

    @ExceptionHandler({ExpiredJwtException.class, MalformedJwtException.class, SignatureException.class})
    public ResponseEntity<Object> handleJwtException(Exception exception) {

        if (exception instanceof ExpiredJwtException) {
            ERROR_DETAILS.put(MESSAGE, exception.getMessage().substring(0, 34));
        } else {
            ERROR_DETAILS.put(MESSAGE, exception.getMessage());
        }
        ERROR_DETAILS.put(TITLE, HttpStatus.UNAUTHORIZED.value());
        ERROR_DETAILS.put(STATUS, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new ResponseDTO(ERROR, new Object[]{ERROR_DETAILS}), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class, BadCredentialsException.class})
    public ResponseEntity<Object> handleAuthenticationException(Exception exception) {

        ERROR_DETAILS.put(MESSAGE, exception.getMessage());
        ERROR_DETAILS.put(TITLE, HttpStatus.FORBIDDEN.value());
        ERROR_DETAILS.put(STATUS, HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(new ResponseDTO(ERROR, new Object[]{ERROR_DETAILS}), HttpStatus.FORBIDDEN);
    }

}
