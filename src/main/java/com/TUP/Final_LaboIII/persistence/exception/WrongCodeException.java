package com.TUP.Final_LaboIII.persistence.exception;

public class WrongCodeException extends RuntimeException{
    public WrongCodeException(String mensaje){
        super(mensaje);
    }
}
