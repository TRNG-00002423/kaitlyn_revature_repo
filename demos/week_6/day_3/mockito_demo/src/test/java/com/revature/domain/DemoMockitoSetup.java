package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Setup Demo")
public class DemoMockitoSetup {
    // @ExtendWith(MockitoExtension.class) enables Mockito in Junit6
    // @Mock creates a Mock object
    // @InjectMocks creates the SUT and injects mocks into it
    // Three ways to create mocks: @Mock, Mockito.mock(), and
    // MockitoAnnotations.openMocks()

    @Mock
    private UserRepository repository;

    @Mock
    private EmailClient client;

    @InjectMocks
    private UserService userService;
    // we want to test UserService in isolation, injecting mocks into it

    @Test
    @DisplayName("Basic mock creating with @Mock annotation")
    void demonstrateMockCreation() {
        // the repository is a mock, not a real implementation
        assertNotNull(repository, "Mock should be created");

        assertNull(repository.findById(1L).orElse(null),
                "unstubbed mock returns empty Optional");

        assertEquals(0, repository.count(),
                "unstubbed mock returns 0 for primitives");

        assertFalse(repository.existsByEmail("test@test.com"),
                "unstubbed mock returns false for booleans");

    }

    @Test
    @DisplayName("Stubbing mock to return specific value")
    void demonstrateBasicStubbing() {
        // arrange: define what the mock should return
        User mockUser = new User(1L, "John Doe", "john@example.com");
        when(repository.findById(1L)).thenReturn(Optional.of(mockUser));

        // act: call the service that uses the mock
        User result = userService.getUser(1L);

        // assert: verify we get the stubbed value
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    @DisplayName("Mock returns stubbed value only for matching arguments")
    void demonstrateArgumentMatching() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(new User("John", "john@test.com")));

        when(repository.findById(2L))
                .thenReturn(Optional.of(new User("Jane", "jane@test.com")));

        assertEquals("John", userService.getUser(1L).getName());
        assertEquals("Jane", userService.getUser(2L).getName());

        assertThrows(UserService.UserNotFoundException.class, () -> {
            userService.getUser(3L);
        });

    }

    // creating mocks programatically
    @Test
    @DisplayName("Alternative - create mock with Mockito.mock()")
    void demonstrateProgramaticMockCreation() {
        // create mock programatically
        UserRepository programaticMock = mock(UserRepository.class);

        // stub it
        when(programaticMock.count()).thenReturn(42L);

        // create service with programatic mock
        UserService service = new UserService(programaticMock);

        // verify
        assertEquals(42L, service.getUserCount());
    }

    @Test
    @DisplayName("@InjectMocks automatically injects @Mock fields")
    void demonstrateInjectMocks() {
        // userService was created by @InjectMocks
        // it automatically recieved @Mock repository and client

        // stub repository
        when(repository.existsByEmail(anyString())).thenReturn(false);
        when(repository.save(any(User.class))).thenAnswer(
                inv -> {
                    User u = inv.getArgument(0);
                    u.setId(100L);
                    return u;
                });

        // stub the email client
        when(client.send(anyString(), anyString(), anyString())).thenReturn(true);

        User created = userService.createUser("Test user", "test@example.com");

        assertNotNull(created);
        assertEquals(100L, created.getId());

        assertEquals("Test user", created.getName());
    }

    @Test
    @DisplayName("Dependency injection enable testing")
    void demonstrateDependencyInjection() {
        UserRepository mockRepo = mock(UserRepository.class);
        EmailClient mockEmail = mock(EmailClient.class);

        UserService testService = new UserService(mockRepo, mockEmail);
        // Now we control exactly what the service uses.
        when(mockRepo.count()).thenReturn(999L);
        assertEquals(999L, testService.getUserCount());
    }

}
