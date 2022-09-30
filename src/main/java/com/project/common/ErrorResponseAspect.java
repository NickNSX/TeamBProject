package com.project.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.project.common.exceptions.AuthenticationException;
import com.project.common.exceptions.AuthorizationException;
import com.project.common.exceptions.DataSourceException;
import com.project.common.exceptions.InvalidRequestException;
import com.project.common.exceptions.ResourceNotFoundException;
import com.project.common.exceptions.ResourcePersistenceException;

@RestControllerAdvice
public class ErrorResponseAspect {

    private static Logger logger = LogManager.getLogger(ErrorResponseAspect.class);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    @ExceptionHandler({InvalidRequestException.class, JsonMappingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequests(Exception e) {
        logger.warn("A bad request was received at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationExceptions(AuthenticationException e) {
        logger.warn("A failed authentication occurred at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(401, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAuthorizationExceptions(AuthorizationException e) {
        logger.warn("An unauthorized request occurred at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(403, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundExceptions(ResourceNotFoundException e) {
        logger.warn("A ResourceNotFoundException occurred at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(403, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleResourcePersistenceExceptions(ResourcePersistenceException e) {
        logger.warn("A ResourcePersistenceException occurred at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(403, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handleDataSourceExceptions(DataSourceException e) {
        logger.error("A datasource exception was thrown at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(500, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Exception e) {
        logger.error("A unhandled exception was thrown at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(500, "An unexpected exception occurred. Devs, please check logs.");
    }
    
}
