package com.iagocarvalho.eccomerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExcepetionsHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity< Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> {
            String filedName = ((FieldError)objectError).getField();
            String message = objectError.getDefaultMessage();
            response.put(filedName, message);
        });
        return  new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MyResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundExcetion(MyResourceNotFoundException e){
    	String message = e.getMessage();
    	return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> ApiExcetion(APIException e){
    	String message = e.getMessage();
    	return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
