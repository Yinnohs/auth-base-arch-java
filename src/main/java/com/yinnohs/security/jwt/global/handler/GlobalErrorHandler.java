package com.yinnohs.security.jwt.global.handler;

import com.yinnohs.security.jwt.auth.domain.exceptions.TokenNotFoundException;
import com.yinnohs.security.jwt.global.dtos.responses.ErrorResponse;
import com.yinnohs.security.jwt.global.dtos.responses.GenericErrorResponse;
import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error)->{
                    var fieldName = ((FieldError) error).getField();
                    var errorMsg = error.getDefaultMessage();
                    errors.put(fieldName,errorMsg);
                });
        var response = new ErrorResponse(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleUserInvalidEmail(InvalidEmailException ex){

        var response = new GenericErrorResponse(ex.getMessage(),"Invalid email" ,400);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException sqlEx){
        var response = new GenericErrorResponse(sqlEx.getMessage(), "InternalServerError", 500);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(TokenNotFoundException sqlEx){
        var response = new GenericErrorResponse(sqlEx.getMessage(), "Token not found", 401);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
