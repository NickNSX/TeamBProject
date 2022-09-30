package com.project.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.ResourceCreationResponse;

import static com.project.common.SecurityUtils.*;
@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<UserResponse> getAllUsers(HttpServletRequest req) {

        logger.info("A GET request was received by /users at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "ADMIN");

        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserResponse getUserById(@PathVariable String id, HttpServletRequest req) {

        logger.info("A GET request was received by /users/{id} at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "ADMIN");

        return userService.getUserById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse registerNewUser(@RequestBody NewUserRequest requestBody, HttpServletRequest req) {

        logger.info("A POST request was received by /users at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "Admin");

        return userService.register(requestBody);
    }    

}