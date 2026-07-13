package com.revature;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@Epic("User Management")
@Feature("User Registration")
public class UserRegistrationTest {
    @Test
    @Story("Users can register with a valid email")
    @Description("Tests that users with a valid email can successfully register")
    @Severity(SeverityLevel.CRITICAL)
    void register_validEmail_success() {

        User testUser = new User("John User", "johnuser@example.com");

    }
}
