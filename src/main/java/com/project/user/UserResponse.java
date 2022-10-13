package com.project.user;

import java.io.Serializable;
import java.util.Objects;

//* Response DTO
public class UserResponse implements Serializable {

    private String userId;
    private String givenName;
    private String surname;
    private String email;
    private String username;
    private boolean isActive;
    private String role;

    public UserResponse(User subject) {
        this.userId = subject.getUserId().toString();
        this.givenName = subject.getGivenName();
        this.surname = subject.getSurname();
        this.email = subject.getEmail();
        this.username = subject.getUsername();
        this.isActive = subject.getIsActive();
        this.role = subject.getRole().getName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(userId, that.userId) && Objects.equals(givenName, that.givenName) && Objects.equals(surname, that.surname) 
            && Objects.equals(email, that.email) && Objects.equals(username, that.username) 
            && Objects.equals(isActive, that.isActive) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, givenName, surname, email, username, isActive, role);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId='" + userId + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", isActive='" + isActive + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}