package org.hypatia.api.Hypatia.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("invalid username or password");
    }

}
