package com.project.common.exceptions;

public class AuthorizationException extends RuntimeException{
    
    public AuthorizationException() {
        super("A illegal request was made to an endpoint");
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
