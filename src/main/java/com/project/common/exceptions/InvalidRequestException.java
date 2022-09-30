package com.project.common.exceptions;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException () {
        super("Invalid request data provided");
    }

    public InvalidRequestException(String message) {
        super(message);
    }    
}