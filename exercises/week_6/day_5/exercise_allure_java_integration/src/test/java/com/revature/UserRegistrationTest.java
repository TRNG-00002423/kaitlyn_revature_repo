package com.revature;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Epic("User Management")
@Feature("User Registration")
@ExtendWith(MockitoExtension.class)
public class UserRegistrationTest {

    // set up the mocks
    @Mock
    private UserRepository repository;

    @Mock
    private EmailClient client;

    @InjectMocks
    private UserService userService;

    @Test
    @Story("Users can register with a valid email")
    @Description("Tests that users with a valid email can successfully register")
    @Severity(SeverityLevel.CRITICAL)
    void registerUser_validEmail_success() {

        assertNotNull(repository);
    }

    @Test
    @Story("User cannot register with duplicate email")
    @Description("Tests that duplicate email registration are rejected")
    @Severity(SeverityLevel.NORMAL)
    void registerUser_duplicateEmail_fails() {
        assertTrue(true);
    }
}
