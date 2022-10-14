package com.revature.MainApp.user;
import com.project.auth.AuthService;
import com.project.auth.Credentials;
import com.project.common.ResourceCreationResponse;
import com.project.user.*;
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
public class UserServiceUnitTest
{

    UserService userService; // SYSTEM UNDER TEST (the thing being tested)
    UserRepository mockUserRepo;

    @BeforeEach
    public void setup()
    {
        mockUserRepo = Mockito.mock(UserRepository.class);
        userService = new UserService(mockUserRepo);
    }

    @AfterEach
    public void cleanUp()
    {
        Mockito.reset(mockUserRepo); // helps to ensure that and when/then on this mock are reset/invalidated
    }

    @Test
    public void test_register_Successful_givenValidAndKnownNewUserRequest() {

        // used to create acutal
        NewUserRequest maxActualStub = new NewUserRequest("max", "gabel", "maxg@revature.com", "mgabel","p@$$w0rd");

        User maxExpectedStub = new User(UUID.randomUUID(), "max", "gabel", "maxg@revature.com", "mgabel", "p@$$w0rd", new Role(UUID.randomUUID(), "QA"));
        when(mockUserRepo.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(maxExpectedStub));
        UserResponse expectedResult = new UserResponse(maxExpectedStub);

        // Actual how to acces find user method
        ResourceCreationResponse actualResult = userService.register(maxActualStub);

        // Assert
        assertNotNull(actualResult);
        assertEquals(mockUserRepo.existsByUsername(maxActualStub.getUsername()), mockUserRepo.existsByUsername(maxExpectedStub.getUsername())); // PLEASE NOTE: the objects you are comparing need to have a .equals method
       // verify(mockUserRepo, times(1)).findUserByUsernameAndPassword(anyString(), anyString());

    }
}
