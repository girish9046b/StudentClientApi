package com.student.app.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Option 1: Use @ResponseStatus for simple cases

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found with the given ID")
public class ResourceNotFoundException extends RuntimeException  {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);
	
    public ResourceNotFoundException(String message) {
    	logger.info("..................ResourceNotFoundExceptionResourceNotFoundException.......");
        super(message);
    }
}
