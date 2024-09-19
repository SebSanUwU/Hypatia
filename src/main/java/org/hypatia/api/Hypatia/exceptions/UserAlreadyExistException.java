package org.hypatia.api.Hypatia.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(){
        super("Usuario ya se encuentra registrado");
    }
}
