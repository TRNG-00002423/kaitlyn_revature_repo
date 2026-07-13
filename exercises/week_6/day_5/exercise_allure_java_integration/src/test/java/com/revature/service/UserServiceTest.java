package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.User;
import com.revature.service.UserService.UserNotFoundException;

import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

// UserServiceTest.java - ADD ALLURE ANNOTATIONS
class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @Story("Users can register with a valid email.")
    @Description("Tests that users can successfully create a user with a valid nama and email.")
    @Severity(SeverityLevel.CRITICAL)
    void createUser_validData_returnsUser() {
        User user = userService.createUser("John", "john@test.com");
        assertNotNull(user.getId());
        assertEquals("John", user.getName());
    }

    @Test
    @Story("Users can't register without including their name.")
    @Description("Attempting to create a user with a null name will throw an exception.")
    @Severity(SeverityLevel.NORMAL)
    void createUser_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null, "test@test.com");
        });
    }

    @Test
    @Story("Users must use a valid email to register.")
    @Description("Attempting to create a user with an invalid email will throw an exception.")
    @Severity(SeverityLevel.CRITICAL)
    void createUser_invalidEmail_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("John", "invalid");
        });
    }

    @Test
    @Story("The user service should be able to retrieve existing users.")
    @Description("Searching for a user with an existing ID should return that user.")
    @Severity(SeverityLevel.NORMAL)
    void getUser_existingUser_returnsUser() {
        User created = userService.createUser("Jane", "jane@test.com");
        User found = userService.getUser(created.getId());
        assertEquals(created, found);
    }

    @Test
    @Story("The user service should be able to retrieve existing users.")
    @Description("Searching for a non-existent user should throw an exception.")
    @Severity(SeverityLevel.MINOR)
    void getUser_nonExistingUser_throwsException() {
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUser(99999L);
        });
    }

    @Test
    @Story("Users should be able to update their name or email with valid inputs.")
    @Description("A user can be updated with a valid name + email.")
    @Severity(SeverityLevel.NORMAL)
    void updateUser_validData_updatesUser() {
        User user = userService.createUser("Old", "old@test.com");
        user.setName("New");
        userService.updateUser(user);

        User updated = userService.getUser(user.getId());
        assertEquals("New", updated.getName());
    }

    @Test
    @Story("Users should be able to be deleted from the service.")
    @Description("Users can be removed")
    @Severity(SeverityLevel.NORMAL)
    void deleteUser_existingUser_removesUser() {
        User user = userService.createUser("ToDelete", "delete@test.com");
        userService.deleteUser(user.getId());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUser(user.getId());
        });
    }

}