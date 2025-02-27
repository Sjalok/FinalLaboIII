package com.TUP.Final_LaboIII.business.exception;

public class WrongDniException extends RuntimeException{
    public WrongDniException(String mensaje){
        super(mensaje);
    }
}
