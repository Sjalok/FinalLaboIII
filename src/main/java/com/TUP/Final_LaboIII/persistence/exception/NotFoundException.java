package com.TUP.Final_LaboIII.persistence.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
