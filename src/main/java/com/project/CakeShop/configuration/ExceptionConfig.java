package com.project.CakeShop.configuration;

import com.project.CakeShop.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity sendBusinessException(BusinessException businessException) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", businessException.getStatus());
        response.put("message", businessException.getMessage());
        return new ResponseEntity(response, HttpStatus.valueOf(businessException.getStatus()));
    }

}
