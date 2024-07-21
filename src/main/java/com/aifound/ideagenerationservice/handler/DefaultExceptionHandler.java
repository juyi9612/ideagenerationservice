package com.aifound.ideagenerationservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aifound.ideagenerationservice.annotation.DefaultErrorController;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HandlerMethod handlerMethod) {
        // Check if the declaring class of the handler method is annotated with @CustomErrorController
        if (handlerMethod.getBeanType().isAnnotationPresent(DefaultErrorController.class)) {
            // Customize your error response here
            return new ResponseEntity<>("System error...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // Default error response if no custom annotation is present
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
