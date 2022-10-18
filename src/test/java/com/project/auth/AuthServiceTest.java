package com.project.auth;
import com.project.common.exceptions.AuthenticationException;
import com.project.common.exceptions.InvalidRequestException;
import com.project.user.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    AuthService sut; // SYSTEM UNDER TEST (the thing being tested)
    UserRepository mockUserRepo;

    @BeforeEach
    public void setup()
    {
        mockUserRepo = Mockito.mock(UserRepository.class);
        sut = new AuthService(mockUserRepo);
    }

    @AfterEach
    public void cleanUp()
    {
        Mockito.reset(mockUserRepo); // helps to ensure that and when/then on this mock are reset/invalidated
    }

    @Test
    public void testAuthenticationReturnSuccessfully() {

        Credentials credentialsStub = new Credentials("Test", "password");

        Role role = new Role();
        role.setId(UUID.fromString("2031a1b1-cdee-4856-bbfe-07150b99c32f"));
        role.setName("Employee");

        User userStub = new User();
        userStub.setUserId(UUID.fromString("2031a1b1-cdee-4856-bbfe-07150b99c322"));
        userStub.setUsername("Test");
        userStub.setPassword("password");
        userStub.setEmail("test@test.com");
        userStub.setGivenName("Tester");
        userStub.setSurname("McTest");
        userStub.setRole(role);
        userStub.setIsActive(true);

        when(mockUserRepo.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(userStub));

        //* Act
        UserResponse actualResult = sut.authenticate(credentialsStub);
        UserResponse expectedResult = new UserResponse(userStub);

        //* Assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAuthenticationReturnFailed_InactiveUser() {

        Credentials credentialsStub = new Credentials("Test", "password");

        Role role = new Role();
        role.setId(UUID.fromString("2031a1b1-cdee-4856-bbfe-07150b99c32f"));
        role.setName("Employee");

        User userStub = new User();
        userStub.setUserId(UUID.fromString("2031a1b1-cdee-4856-bbfe-07150b99c322"));
        userStub.setUsername("Test");
        userStub.setPassword("password");
        userStub.setEmail("test@test.com");
        userStub.setGivenName("Tester");
        userStub.setSurname("McTest");
        userStub.setRole(role);
        userStub.setIsActive(false);

        assertThrows(AuthenticationException.class, () -> {
            sut.authenticate(credentialsStub);
        });
    }

    @Test
    public void testAuthenticationReturnFailed_Null() {

        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(null);
        });
    }

    @Test
    public void testAuthenticationReturnFailed_ShortUsername() {


        Credentials credentialsStub = new Credentials("x", "credentials");

        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(credentialsStub);
        });
    }   
    
    @Test
    public void testAuthenticationReturnFailed_ShortPassword() {


        Credentials credentialsStub = new Credentials("valid", "s");

        assertThrows(InvalidRequestException.class, () -> {
            sut.authenticate(credentialsStub);
        });
    } 
}
