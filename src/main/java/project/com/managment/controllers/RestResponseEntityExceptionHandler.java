package project.com.managment.controllers;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.com.managment.services.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * Created by jt on 10/6/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler  {



    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Problem with the data provided", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    /*
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("General exception", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    */
}