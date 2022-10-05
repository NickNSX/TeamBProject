package com.revature.MainApp.auth;

import com.project.auth.AuthService;
import com.project.auth.Credentials;
import com.project.common.exceptions.InvalidRequestException;
import com.project.user.Role;
import com.project.user.User;
import com.project.user.UserRepository;
import com.project.user.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.naming.AuthenticationException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceUnitTest {

    AuthService sut; // SYSTEM UNDER TEST (the thing being tested)
    UserRepository mockUserRepo;

    @BeforeEach
    public void setup() {
        mockUserRepo = Mockito.mock(UserRepository.class);
        sut = new AuthService(mockUserRepo);
    }

    @AfterEach
    public void cleanUp() {
        Mockito.reset(mockUserRepo); // helps to ensure that and when/then on this mock are reset/invalidated
    }

    @Test
    public void test_authenticate_returnsSuccessfully_givenValidAndKnownCredentials() {

        // Arrange
        Credentials credentialsStub = new Credentials("valid", "credentials");
        User userStub = new User(UUID.randomUUID(), "Val", "Id", "valid123@revature.net", "valid", "credentials", new Role(UUID.randomUUID(), "QA"));
        when(mockUserRepo.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(userStub));
        UserResponse expectedResult = new UserResponse(userStub);

        // Act
        UserResponse actualResult = sut.authenticate(credentialsStub);

        // Assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult); // PLEASE NOTE: the objects you are comparing need to have a .equals method
        verify(mockUserRepo, times(1)).findUserByUsernameAndPassword(anyString(), anyString());

    }

    @Test
    public void test_authenticate_throwsInvalidRequestException_givenTooShortOfPassword() {

        // Arrange
        Credentials credentialsStub = new Credentials("invalid", "creds");

        // Act & Assert
        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(credentialsStub);
        });

        verify(mockUserRepo, times(0)).findUserByUsernameAndPassword(anyString(), anyString());


    }

    @Test
    public void test_authenticate_throwsInvalidRequestException_givenTooShortOfUsername() {

        // Arrange
        Credentials credentialsStub = new Credentials("x", "p4$$2W0RD");

        // Act & Assert
        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(credentialsStub);
        });

        verify(mockUserRepo, times(0)).findUserByUsernameAndPassword(anyString(), anyString());


    }

    @Test
    public void test_authenticate_throwsInvalidRequestException_givenNullCredentials() {

        // Arrange
        Credentials credentialsStub = null;

        // Act & Assert
        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(credentialsStub);
        });

        verify(mockUserRepo, times(0)).findUserByUsernameAndPassword(anyString(), anyString());


    }

    @Test
    public void test_authenticate_throwsAuthenticationException_givenValidUnknownCredentials() {

        // Arrange
        Credentials credentialsStub = new Credentials("unknown", "credentials");
        when(mockUserRepo.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        // Act
        assertThrows(AuthenticationException.class, () -> {
            sut.authenticate(credentialsStub);
        });

        // Assert
        verify(mockUserRepo, times(1)).findUserByUsernameAndPassword(anyString(), anyString());

    }


}
