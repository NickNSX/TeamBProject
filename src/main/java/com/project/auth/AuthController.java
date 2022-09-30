package com.project.auth;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.UserResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private static Logger logger = LogManager.getLogger(AuthController.class);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserResponse authenticate(@RequestBody Credentials credentials, HttpServletRequest req) {
        
        HttpSession usersSession = req.getSession();
        
        UserResponse authUser = authService.authenticate(credentials);

        logger.info("Establishing user session for user: {}", authUser.getUsername());
        usersSession.setAttribute("authUser", authUser);

        return authUser;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest req) {
        req.getSession().invalidate();
    }

}