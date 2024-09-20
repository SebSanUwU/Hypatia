package org.hypatia.api.Hypatia.exceptions;

public abstract class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super(message);
    }
}