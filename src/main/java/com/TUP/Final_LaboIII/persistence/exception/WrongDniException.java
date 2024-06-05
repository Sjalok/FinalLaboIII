package com.TUP.Final_LaboIII.persistence.exception;

public class WrongDniException extends RuntimeException{
    public WrongDniException(String mensaje){
        super(mensaje);
    }
}
