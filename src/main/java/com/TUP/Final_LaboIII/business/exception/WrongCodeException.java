package com.TUP.Final_LaboIII.business.exception;

public class WrongCodeException extends RuntimeException{
    public WrongCodeException(String mensaje){
        super(mensaje);
    }
}
