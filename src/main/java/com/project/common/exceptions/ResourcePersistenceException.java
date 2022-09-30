package com.project.common.exceptions;

public class ResourcePersistenceException extends RuntimeException{

    public ResourcePersistenceException() {
        super ("The provideed resource could not be persisted.");
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }
    
}
