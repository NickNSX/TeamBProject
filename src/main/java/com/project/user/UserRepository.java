package com.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findUserByid(UUID userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    // @Query(nativeQuery = true, value = "SELECT * FROM app_users WHERE username = :1 AND password :2")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_users SET email = ?1 WHERE user_id = ?2")
    void updateUserEmail(String email, UUID userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_users SET given_name = ?1 WHERE user_id = ?2")
    void updateUserGivenName(String givenName, UUID userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_users SET surname = ?1 WHERE user_id = ?2")
    void updateSurname(String surname, UUID userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_users SET role_id = ?1 WHERE user_id = ?2")
    void updateUserRole(UUID role, UUID userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_users SET is_active = ?1 WHERE user_id = ?2")
    void updateUserIsActive(boolean isActive, UUID userId);

}
