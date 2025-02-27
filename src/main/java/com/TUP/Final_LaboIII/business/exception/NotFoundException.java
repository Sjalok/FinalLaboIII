package com.TUP.Final_LaboIII.business.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
