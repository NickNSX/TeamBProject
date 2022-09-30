package com.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    // @Query(nativeQuery = true, value = "SELECT * FROM app_users WHERE username = :1 AND password :2")
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
