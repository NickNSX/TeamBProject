package com.project.auth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.common.exceptions.AuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.exceptions.InvalidRequestException;
import com.project.user.UserRepository;
import com.project.user.UserResponse;


@Service
public class AuthService {
    
    private static Logger logger = LogManager.getLogger(AuthService.class);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse authenticate(Credentials credentials) {

        
        logger.info("Starting authentication at {}", LocalDateTime.now().format(format));

        if(credentials == null) {
            logger.warn("Invalid credentials provided at {}", LocalDateTime.now().format(format));
            throw new InvalidRequestException("The provided credentials cannot be empty");
        }

        if(credentials.getUsername().length() < 4) {
            logger.warn("Invalid username provided at {}", LocalDateTime.now().format(format));
            throw new InvalidRequestException("The provided username must be at least 4 characters");
        }

        if(credentials.getPassword().length() < 4) {
            logger.warn("Invalid password provided at {}", LocalDateTime.now().format(format));
            throw new InvalidRequestException("The provided password must be at least 4 characters");
        }
            
        UserResponse user = userRepository.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .map(UserResponse :: new).orElseThrow(AuthenticationException::new);

        if (user.getIsActive() == false) {throw new AuthenticationException("User is inactive");}

        return user;
    }
}