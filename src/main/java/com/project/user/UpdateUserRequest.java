package com.project.user;

import com.project.common.Request;

public class UpdateUserRequest implements Request<User> {

    private String username;
    private String email;
    private String password;
    private String given_name;
    private String surname;
    private boolean is_active;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getGiven_name() {
        return given_name;
    }


    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public boolean getIs_active() {
        return is_active;
    }


    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Updated {" +
                "email = '" + email + "' " +
                "given_name = '" + is_active + "' " +
                "surname = '" + surname + "' " +
                "password = '" + password + "' " +
                "is_active = '" + is_active + "' " +
                "role = '" + role + "' " +
                "}";
    }

    @Override
    public User extractEntity() {
        User extractedEntity = new User();
        extractedEntity.setUsername(this.username);
        extractedEntity.setEmail(this.email);
        extractedEntity.setGivenName(this.given_name);
        extractedEntity.setSurname(this.surname);
        // extractedEntity.setIs_active(this.is_active);
        // extractedEntity.setRole(role);
        return extractedEntity;
    }
}
