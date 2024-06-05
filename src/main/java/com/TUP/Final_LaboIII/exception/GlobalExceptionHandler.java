package com.TUP.Final_LaboIII.exception;

import com.TUP.Final_LaboIII.persistence.exception.NotFoundException;
import com.TUP.Final_LaboIII.persistence.exception.WrongCodeException;
import com.TUP.Final_LaboIII.persistence.exception.WrongDniException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({WrongDniException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleAlumnoWrongDniException(WrongDniException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({WrongCodeException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleWrongCodeException(WrongCodeException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
