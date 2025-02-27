package com.TUP.Final_LaboIII.controller.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
