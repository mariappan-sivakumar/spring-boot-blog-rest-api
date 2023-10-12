package com.springboot.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;
    Logger logger= LoggerFactory.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s is not fount with %s: '%s'",resourceName,fieldName,fieldValue));
        logger.error(String.format("%s is not fount with %s: '%s'",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
