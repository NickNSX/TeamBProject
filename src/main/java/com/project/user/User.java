package com.project.user;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//* POJO = Plain Ol' Java Object
@Entity
@Table(name = "ers_users")
public class User {
    @Id // indicates a primary key
    @Column(name = "user_id") // all fields in an entity implicitly have @Column
    private UUID id;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(nullable = false) // column name defaults to field name if not provided
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // JPA REQUIRES A NO-ARG CONSTRUCTOR FOR ALL ENTITIES
    public User() {
        super();
    }

    public User(UUID id, String givenName, String surname, String email, String username, String password, Boolean isActive, Role role) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole (Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(givenName, user.givenName)
            && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) 
            && Objects.equals(username, user.username) && Objects.equals(password, user.password) 
            && Objects.equals(isActive, user.isActive) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, givenName, surname, email, username, password, isActive, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

/** set user's id, name, and task from the user or get their 
 * information from the database. Displays the current data
 * from the database using the above format.
 */