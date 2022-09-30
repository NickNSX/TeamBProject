package com.project.user;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.ResourceCreationResponse;
import com.project.common.exceptions.InvalidRequestException;
import com.project.common.exceptions.ResourceNotFoundException;
import com.project.common.exceptions.ResourcePersistenceException;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll()
                      .stream()
                      .map(UserResponse::new)
                      .collect(Collectors.toList());
    }

    public UserResponse getUserById(String id) {
        try {
            return userRepo.findById(UUID.fromString(id))
                           .map(UserResponse::new)
                           .orElseThrow(ResourceNotFoundException::new);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("A valid uuid must be provided!");
        }
    }

    public ResourceCreationResponse register(NewUserRequest newUser) {

        if (newUser == null) {
            throw new InvalidRequestException("Provided request payload was null.");
        }

        if (newUser.getGivenName() == null || newUser.getGivenName().length() <= 0 ||
            newUser.getSurname() == null || newUser.getSurname().length() <= 0)
        {
            throw new InvalidRequestException("A non-empty given name and surname must be provided");
        }

        if (newUser.getEmail() == null || newUser.getEmail().length() <= 0) {
            throw new InvalidRequestException("A non-empty email must be provided.");
        }

        if (newUser.getUsername() == null || newUser.getUsername().length() < 4) {
            throw new InvalidRequestException("A username with at least 4 characters must be provided.");
        }

        if (newUser.getPassword() == null || newUser.getPassword().length() < 8) {
            throw new InvalidRequestException("A password with at least 8 characters must be provided.");
        }

        if (userRepo.existsByEmail(newUser.getEmail())) {
            throw new ResourcePersistenceException("Resource not persisted! The provided email is already taken.");
        }

        if (userRepo.existsByUsername(newUser.getUsername())) {
            throw new ResourcePersistenceException("Resource not persisted! The provided username is already taken.");
        }

        User userToPersist = newUser.extractEntity();

        if(newUser.getRole() != null) {
            if (newUser.getRole().toUpperCase().equals("ADMIN")) {
                Role role = new Role();
                role.setId(UUID.fromString("UUID for Adim")); // TODO add uuid for Admin 8-4-4-4-12
                role.setName("Admin");
                userToPersist.setRole(role);
            } else if (newUser.getRole().toUpperCase().equals("FINANCE MANAGER")) {
                Role role = new Role();
                role.setId(UUID.fromString("UUID for Finance Manger")); // TODO add uuid for Finance Manager 8-4-4-4-12
                role.setName("Finance Manager");
                userToPersist.setRole(role);
            } else if (newUser.getRole().toUpperCase().equals("EMPLOYEE")) {
                Role role = new Role();
                role.setId(UUID.fromString("UUID for Employee")); // TODO add Employee uuid 8-4-4-4-12
                role.setName("Employee");
                userToPersist.setRole(role);
            }else { // If given role does not match
                throw new InvalidRequestException("Role not supported. Enter 'Admin', 'Finance Manger' or 'Employee'.");
            }
        } else { // Default to employee if no given role
            Role role = new Role();
            role.setId(UUID.fromString("UUID for Employee")); // TODO add Employee uuid 8-4-4-4-12
            role.setName("Employee");
            userToPersist.setRole(role);
        }

        userRepo.save(userToPersist);
        return new ResourceCreationResponse(userToPersist.getId().toString());

    }
}