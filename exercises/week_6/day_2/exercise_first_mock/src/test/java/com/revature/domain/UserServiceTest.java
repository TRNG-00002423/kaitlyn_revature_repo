package com.revature.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.domain.UserService.DuplicateUserException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
class UserServiceTest {

    @Mock
    private UserRepository repository; // Mock the dependency

    @Mock
    private EmailClient emailClient; // Mock the dependency

    @InjectMocks
    private UserService userService; // Inject mocks automatically

    @Test
    void getUser_existingUser_returnsUser() {
        // Arrange: Configure the mock
        User expectedUser = new User("John", "john@test.com");
        expectedUser.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(expectedUser));

        // Act: Call the method under test
        User actualUser = userService.getUser(1L);

        // Assert: Verify the result
        assertEquals(expectedUser, actualUser);
        assertEquals("John", actualUser.getName());
    }

    @Test
    void getUser_nonExistentUser_throwsException() {
        // Arrange: Mock returns empty Optional
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserService.UserNotFoundException.class, () -> {
            userService.getUser(999L);
        });
    }

    @Test
    void createUser_newUser_returnsUser() {
        when(repository.existsByEmail(anyString())).thenReturn(false);
        when(repository.save(any(User.class))).thenAnswer(invocation -> {
            User input = invocation.getArgument(0);
            input.setId(42L);
            return input;
        });

        assertEquals(42L, userService.createUser("John", "john@example.com").getId());
    }

    @Test
    void createUser_duplicateEmail_throwsDuplicateUserException() {
        when(repository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(DuplicateUserException.class, () -> {
            userService.createUser("Jane", "jane@example.com");
        });
    }

    @Test
    void createUser_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null, "ksadeugfaokldjhfgsa");
        });
    }

    @Test
    void getActiveUsers_twoUsers_getAll() {
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new User(1L, "John Doe", "john@example.com"));
        expectedList.add(new User(2L, "Jane Doe", "jane@example.com"));
        when(repository.findAllActive()).thenReturn(expectedList);

        List<User> actualList = userService.getActiveUsers();
        assertNotNull(actualList);
        assertEquals(2, actualList.size());
        assertEquals("John Doe", actualList.get(0).getName());
        assertEquals("Jane Doe", actualList.get(1).getName());
    }

    @Test
    void getActiveUsers_noActiveUsers_getEmpty() {
        List<User> expectedList = new ArrayList<>();
        when(repository.findAllActive()).thenReturn(expectedList);

        List<User> actualList = userService.getActiveUsers();
        assertEquals(0, actualList.size());
    }

    @Test
    void getUserCount_someCount_getCorrect() {
        when(repository.count()).thenReturn(1225L);
        assertEquals(1225L, userService.getUserCount());
    }

}