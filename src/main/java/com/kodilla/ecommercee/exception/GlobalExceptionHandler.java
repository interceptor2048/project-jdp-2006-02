package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyCartException.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(Exception ex, HttpServletRequest request) {
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({GroupNotFoundException.class, OrderNotFoundException.class,
            ProductNotFoundException.class, UserNotFoundException.class})
    @ResponseBody
    public ErrorInfo handleNotFound(Exception ex, HttpServletRequest request) {
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }
}
