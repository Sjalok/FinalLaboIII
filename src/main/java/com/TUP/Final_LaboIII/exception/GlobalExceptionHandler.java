package com.TUP.Final_LaboIII.exception;

import com.TUP.Final_LaboIII.business.exception.*;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({YaExistenteException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleYaExistenteException(YaExistenteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(java.lang.NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNumberFormatException(java.lang.NumberFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El dato ingresado solo puede contener numeros.");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({CorrelatividadesException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleCorrelatividadesException(CorrelatividadesException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
