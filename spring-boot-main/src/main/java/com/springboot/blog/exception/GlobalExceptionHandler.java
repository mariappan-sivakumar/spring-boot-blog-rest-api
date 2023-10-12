package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails>  handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails>  handleBlogApiException(BlogApiException exception,
                                                                         WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));
        logger.error(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails>  handleAccessDeniedException(AccessDeniedException exception,
                                                                WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));
        logger.error(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>  handleGlobalException(Exception exception,
                                                                         WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String , String > errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
           String fieldName= ((FieldError)error).getField();
           String message=error.getDefaultMessage();
           errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
//                                                               WebRequest request){
//        Map<String , String > errorDetails=new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach(error->{
//            String fieldName= ((FieldError)error).getField();
//            String message=error.getDefaultMessage();
//            errorDetails.put(fieldName,message);
//        });
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

}
