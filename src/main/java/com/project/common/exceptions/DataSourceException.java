package com.project.common.exceptions;

public class DataSourceException extends RuntimeException{

    public DataSourceException(Throwable cause) {
        super("Something went wrong when connecting to database. Developers please check logs.", cause);
    }

    public DataSourceException (String message, Throwable cause) {
        super(message, cause);
    }
    
}
